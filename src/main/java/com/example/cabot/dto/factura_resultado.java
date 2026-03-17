package com.example.cabot.dto;

import java.util.List;

public record factura_resultado(
        String archivo,
        int totalProcesados,
        int totalErrores,
        List<producto_validado> detalles
) {
}
