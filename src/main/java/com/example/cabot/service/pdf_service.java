package com.example.cabot.service;

import com.example.cabot.dto.factura_resultado;
import com.example.cabot.dto.producto_validado;
import com.example.cabot.repository.reglas_facturacion_repo;
import jakarta.persistence.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.*;

@Service
public class pdf_service {
    @Autowired
    reglas_facturacion_repo reglas_repo;

    public factura_resultado process_pdf_to_json(MultipartFile file) {
        List<producto_validado> detalles = new ArrayList<>();
        int erroresCount = 0;

        Map<String, Double> descuento_prefijo = new HashMap<>();
        Map<String, Double> descuento_marca = new HashMap<>();

        reglas_repo.findAll().forEach(regla -> {
            double desc = regla.getDescuento_esperado().doubleValue();
            if (regla.getPrefijo() != null) {
                descuento_prefijo.put(regla.getPrefijo().toUpperCase(), desc);
            }
            if (regla.getMarca() != null) {
                descuento_marca.put(regla.getMarca().toUpperCase(), desc);
            }
        });

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            String texto = stripper.getText(document);

            Pattern pattern = Pattern.compile("([A-Z]{3}\\d+)\\s+([A-Z0-9/'-]+)\\s+.*?(\\d+,\\d+)%\\s+\\d+,\\d+");
            Matcher matcher = pattern.matcher(texto);

            while (matcher.find()) {
                String codigo = matcher.group(1);
                String marca = matcher.group(2).toUpperCase(); // Normalizar a mayúsculas
                double aplicado = Double.parseDouble(matcher.group(3).replace(",", "."));

                String prefijo = codigo.substring(0, 3).toUpperCase();

                Double esperado = descuento_prefijo.getOrDefault(prefijo,
                        descuento_marca.getOrDefault(marca, -1.0));

                String estado = "OK";

                if (esperado == -1.0) {
                    estado = "NO REGLA";
                } else if (Math.abs(aplicado - esperado) > 0.01) {
                    estado = "ERROR";
                    erroresCount++;
                }

                detalles.add(new producto_validado(codigo, marca, aplicado, esperado, estado));
            }

            return new factura_resultado(
                    file.getOriginalFilename(),
                    detalles.size(),
                    erroresCount,
                    detalles
            );

        } catch (Exception e) {
            return new factura_resultado("Error: " + e.getMessage(), 0, 0, List.of());
        }
    }
}