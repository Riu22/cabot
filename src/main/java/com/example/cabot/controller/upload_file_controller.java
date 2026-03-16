package com.example.cabot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class upload_file_controller {

    @PostMapping
    public ResponseEntity<String> upload_file(@RequestParam(value = "file", required = false) MultipartFile file) {

        if (file == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El parámetro 'file' es obligatorio.");
        }

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Por favor, selecciona un archivo PDF válido.");
        }

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Archivo recibido: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error procesando el PDF: " + e.getMessage());
        }
    }
}