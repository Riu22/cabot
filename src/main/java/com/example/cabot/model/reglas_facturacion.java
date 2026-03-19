package com.example.cabot.model;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.math.BigDecimal;

@Entity
public class reglas_facturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 20)
    String prefijo;
    String marca;
    @Column(columnDefinition = "TEXT")
    String descripcion;
    @Column(precision = 5,scale = 2)
    BigDecimal descuento_esperado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getDescuento_esperado() {
        return descuento_esperado;
    }

    public void setDescuento_esperado(BigDecimal descuento_esperado) {
        this.descuento_esperado = descuento_esperado;
    }
}
