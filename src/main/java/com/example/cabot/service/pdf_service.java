package com.example.cabot.service;

import com.example.cabot.dto.factura_resultado;
import com.example.cabot.dto.producto_validado;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.regex.*;

@Service
public class pdf_service {

    private static final Map<String, Double> DESC_POR_PREFIJO = Map.of(
            "FUX", 57.25,
            "JAX", 70.00,
            "PPX", 53.00,
            "VAX", 50.00
    );

    private static final Map<String, Double> EXCEPCIONES_MARCA = Map.of(
            "GENEBRE", 52.00,
            "ARCO", 50.00
    );

    public factura_resultado process_pdf_to_json(MultipartFile file) {
        List<producto_validado> detalles = new ArrayList<>();
        int erroresCount = 0;

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            String texto = stripper.getText(document);

            Pattern pattern = Pattern.compile("([A-Z]{3}\\d+)\\s+([A-Z]+)\\s+.*?(\\d+,\\d+)%\\s+\\d+,\\d+");
            Matcher matcher = pattern.matcher(texto);

            while (matcher.find()) {
                String codigo = matcher.group(1);
                String marca = matcher.group(2);
                double aplicado = Double.parseDouble(matcher.group(3).replace(",", "."));

                String prefijo = codigo.substring(0, 3);
                Double esperado = DESC_POR_PREFIJO.getOrDefault(prefijo,
                        EXCEPCIONES_MARCA.getOrDefault(marca, -1.0));

                String estado = "OK";
                if (esperado != -1.0 && Math.abs(aplicado - esperado) > 0.01) {
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