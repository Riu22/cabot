package com.example.cabot.service;

import com.example.cabot.dto.regla_nueva;
import com.example.cabot.model.reglas_facturacion;
import com.example.cabot.repository.reglas_facturacion_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class admin_service {
    @Autowired
    reglas_facturacion_repo reglas_facturacion_repo;

    public reglas_facturacion crear_regla(String prefijo, String marca,String descripcion, Double descuento_esperado) {
        reglas_facturacion regla = new reglas_facturacion();
        regla.setPrefijo(prefijo);
        regla.setMarca(marca);
        regla.setDescripcion(descripcion);
        regla.setDescuento_esperado(BigDecimal.valueOf(descuento_esperado));
        return reglas_facturacion_repo.save(regla);
    }
}
