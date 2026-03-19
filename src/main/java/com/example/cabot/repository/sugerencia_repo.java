package com.example.cabot.repository;

import com.example.cabot.model.sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface sugerencia_repo extends JpaRepository<sugerencia, Integer> {
    boolean existsByCodigoDetectadoAndDescuentoEncontrado(String codigo, BigDecimal descuento);
    boolean existsByCodigoDetectado(String codigo);
    Optional<sugerencia> findByCodigoDetectadoAndDescuentoEncontrado(String codigo, BigDecimal descuento);
}
