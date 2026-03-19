package com.example.cabot.controller;

import com.example.cabot.dto.regla_nueva;
import com.example.cabot.model.reglas_facturacion;
import com.example.cabot.service.admin_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class admin {
    @Autowired
    admin_service admin_service;
    @PostMapping("/admin/crear-regla")
    public ResponseEntity<reglas_facturacion> crearRegla(@RequestBody regla_nueva dto) {
        reglas_facturacion nueva_regla = admin_service.crear_regla(dto.prefijo(), dto.marca(), dto.descripcion(), dto.descuento_esperado());
        return new ResponseEntity<>(nueva_regla, HttpStatus.CREATED);
    }
}
