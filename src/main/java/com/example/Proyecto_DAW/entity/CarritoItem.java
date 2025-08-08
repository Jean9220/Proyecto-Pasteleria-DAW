package com.example.E_commerce_Proyecto.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "carrito_items")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia al ID del producto del microservicio de Admin.
    @Column(name = "id_producto")
    private Long idProducto;

    // Cantidad de este producto en el carrito
    private Integer cantidad;

    // Guarda una copia del precio en el momento que fue añadido al carrito
    private BigDecimal precioUnitario;

    // Guarda una copia del nombre del producto
    private String nombreProducto;

    // Relación con la entidad Carrito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    public CarritoItem() {
    }

    public CarritoItem(Carrito carrito, String nombreProducto, BigDecimal precioUnitario, Integer cantidad, Long id, Long idProducto) {
        this.carrito = carrito;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.id = id;
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}