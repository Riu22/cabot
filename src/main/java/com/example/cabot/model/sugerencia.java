package com.example.cabot.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sugerencias_reglas")
public class sugerencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "codigo_detectado", length = 20)
    private String codigoDetectado;

    @Column(name = "marca_detectada")
    private String marcaDetectada;
    @Column(precision = 5, scale = 2,name = "descuento_encontrado")
    BigDecimal descuentoEncontrado;
    @Column(name = "fecha_deteccion")
    LocalDateTime fecha_deteccion;
    String nombreFactura;
    int vecesRepetido;

    public String getNombreFactura() {
        return nombreFactura;
    }

    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

    public int getVecesRepetido() {
        return vecesRepetido;
    }

    public void setVecesRepetido(int vecesRepetido) {
        this.vecesRepetido = vecesRepetido;
    }

    public sugerencia(String codigo, String marca, BigDecimal descuento, LocalDateTime fecha,String nombreFactura,int vecesRepetido) {
        this.codigoDetectado = codigo;
        this.marcaDetectada = marca;
        this.descuentoEncontrado = descuento;
        this.fecha_deteccion = fecha;
        this.nombreFactura = nombreFactura;
        this.vecesRepetido = vecesRepetido;
    }
    public sugerencia() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoDetectado() {
        return codigoDetectado;
    }

    public void setCodigoDetectado(String codigoDetectado) {
        this.codigoDetectado = codigoDetectado;
    }

    public String getMarcaDetectada() {
        return marcaDetectada;
    }

    public void setMarcaDetectada(String marcaDetectada) {
        this.marcaDetectada = marcaDetectada;
    }

    public BigDecimal getDescuentoEncontrado() {
        return descuentoEncontrado;
    }

    public void setDescuentoEncontrado(BigDecimal descuentoEncontrado) {
        this.descuentoEncontrado = descuentoEncontrado;
    }

    public LocalDateTime getFecha_deteccion() {
        return fecha_deteccion;
    }

    public void setFecha_deteccion(LocalDateTime fecha_deteccion) {
        this.fecha_deteccion = fecha_deteccion;
    }
}
