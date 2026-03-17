package com.example.cabot.controller;

import com.example.cabot.dto.factura_resultado;
import com.example.cabot.service.pdf_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class upload_file_controller {

    @Autowired
    private pdf_service pdfService;

    @PostMapping
    // Cambiamos ResponseEntity<String> a ResponseEntity<Object> para poder devolver el JSON o Errores
    public ResponseEntity<Object> upload_file(@RequestParam(value = "file", required = false) MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Seleccione un archivo PDF válido.");
        }

        try {
            // Procesamos el archivo y obtenemos el Record
            factura_resultado resultado = pdfService.process_pdf_to_json(file);

            // Devolvemos el objeto directamente. Spring lo convierte a JSON.
            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error crítico al procesar el PDF: " + e.getMessage());
        }
    }
}