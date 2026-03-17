package com.example.cabot.dto;

public record producto_validado(
        String codigo,
        String marca,
        double dtoAplicado,
        double dtoEsperado,
        String estado
) {
}
