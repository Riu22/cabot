package com.example.cabot.dto;

public record regla_nueva(
        String prefijo,
        String marca,
        String descripcion,
        Double descuento_esperado
) {
}
