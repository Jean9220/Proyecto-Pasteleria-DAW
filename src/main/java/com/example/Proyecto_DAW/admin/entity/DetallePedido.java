package com.example.Proyecto_DAW.admin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal subtotal;

    public DetallePedido() {
        // Constructor por defecto
    }

    public DetallePedido(BigDecimal subtotal, int cantidad, Producto producto, Pedido pedido, Long idDetalle) {
        this.idDetalle = idDetalle;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
