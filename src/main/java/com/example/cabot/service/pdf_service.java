package com.example.cabot.service;

import com.example.cabot.dto.factura_resultado;
import com.example.cabot.dto.producto_validado;
import com.example.cabot.model.sugerencia;
import com.example.cabot.repository.reglas_facturacion_repo;
import com.example.cabot.repository.sugerencia_repo;
import jakarta.persistence.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;

@Service
public class pdf_service {
    @Autowired
    reglas_facturacion_repo reglas_repo;

    @Autowired
    sugerencia_repo sugerencia_repo;

    public factura_resultado process_pdf_to_json(MultipartFile file) {
        List<producto_validado> detalles = new ArrayList<>();
        int erroresCount = 0;

        // 1. Cargar reglas en Mapas para búsqueda rápida
        Map<String, Double> reglas_por_codigo = new HashMap<>();
        Map<String, Double> reglas_por_marca = new HashMap<>();

        reglas_repo.findAll().forEach(r -> {
            double d = r.getDescuento_esperado().doubleValue();
            if (r.getPrefijo() != null) reglas_por_codigo.put(r.getPrefijo().toUpperCase(), d);
            if (r.getMarca() != null) reglas_por_marca.put(r.getMarca().toUpperCase(), d);
        });

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            String texto = stripper.getText(document);

            Pattern pattern = Pattern.compile("([A-Z]{3}\\d+)\\s+([A-Z0-9/'-]+)\\s+.*?(\\d+,\\d+)%\\s+\\d+,\\d+");
            Matcher matcher = pattern.matcher(texto);

            while (matcher.find()) {
                String codigo = matcher.group(1).toUpperCase();
                String marca = matcher.group(2).toUpperCase();
                double aplicado = Double.parseDouble(matcher.group(3).replace(",", "."));
                String prefijo = codigo.substring(0, 3);

                Double esperado = -1.0;
                String estado = "OK";

                // --- LÓGICA DE DECISIÓN ---

                if (reglas_por_codigo.containsKey(codigo)) {
                    // CASO A: Existe la regla exacta para el producto
                    esperado = reglas_por_codigo.get(codigo);
                    if (Math.abs(aplicado - esperado) > 0.01) {
                        estado = "ERROR"; // Si existe el código exacto y no coincide, es un error real
                        erroresCount++;
                    }
                }
                else if (reglas_por_codigo.containsKey(prefijo)) {
                    // CASO B: Existe regla de prefijo (ej. JAX)
                    esperado = reglas_por_codigo.get(prefijo);
                    if (Math.abs(aplicado - esperado) > 0.01) {
                        estado = "SUGERENCIA_NUEVA_EXCEPCION";
                        gestionarSugerencia(codigo, marca, aplicado, file.getOriginalFilename());
                    }
                }
                else if (reglas_por_marca.containsKey(marca)) {
                    // CASO C: Existe regla por marca (ej. ARMAFLEX)
                    esperado = reglas_por_marca.get(marca);
                    if (Math.abs(aplicado - esperado) > 0.01) {
                        estado = "ERROR";
                        erroresCount++;
                    }
                }
                else {
                    // CASO D: No hay ninguna regla en la BD
                    estado = "SIN_REGLA_DEFINIDA";
                    esperado = 0.0;
                    gestionarSugerencia(codigo, marca, aplicado, file.getOriginalFilename());
                }

                detalles.add(new producto_validado(codigo, marca, aplicado, esperado, estado));
            }

            return new factura_resultado(file.getOriginalFilename(), detalles.size(), erroresCount, detalles);
        } catch (Exception e) {
            return new factura_resultado("Error: " + e.getMessage(), 0, 0, List.of());
        }
    }

    private void gestionarSugerencia(String codigo, String marca, double aplicado, String factura) {
        BigDecimal dto = BigDecimal.valueOf(aplicado);
        Optional<sugerencia> opt = sugerencia_repo.findByCodigoDetectadoAndDescuentoEncontrado(codigo, dto);

        sugerencia s = opt.orElse(new sugerencia());
        if (opt.isEmpty()) {
            s.setCodigoDetectado(codigo);
            s.setMarcaDetectada(marca);
            s.setDescuentoEncontrado(dto);
            s.setVecesRepetido(0);
        }
        s.setVecesRepetido(s.getVecesRepetido() + 1);
        s.setNombreFactura(factura);
        s.setFecha_deteccion(LocalDateTime.now());
        sugerencia_repo.save(s);
    }
}