package com.example.cabot.repository;

import com.example.cabot.model.reglas_facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface reglas_facturacion_repo extends JpaRepository<reglas_facturacion, Integer> {
}
