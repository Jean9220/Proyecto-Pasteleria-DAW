package com.example.Proyecto_DAW.admin.service;

import com.example.Proyecto_DAW.admin.entity.Carrito;
import com.example.Proyecto_DAW.admin.entity.CarritoItem;
import com.example.Proyecto_DAW.admin.entity.Producto;
import com.example.Proyecto_DAW.admin.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarritoService {

    private Carrito carrito = new Carrito();

    @Autowired
    private ProductoService productoService;

    public Carrito getCarrito() {
        return carrito;
    }

    public void agregarProducto(Long idProducto, int cantidad) {
        Producto producto = productoService.getProductById(idProducto);
        Optional<CarritoItem> existente = carrito.getItems().stream()
                .filter(item -> item.getIdProducto().equals(idProducto))
                .findFirst();

        if (existente.isPresent()) {
            CarritoItem item = existente.get();
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            CarritoItem nuevo = new CarritoItem();
            nuevo.setIdProducto(idProducto);
            nuevo.setCantidad(cantidad);
            nuevo.setPrecioUnitario(producto.getPrecio());
            nuevo.setNombreProducto(producto.getNombre());
            nuevo.setImagen(producto.getImagen());
            nuevo.setCarrito(carrito);
            carrito.getItems().add(nuevo);
        }
    }

    public void quitarProducto(Long idProducto) {
        carrito.getItems().removeIf(item -> item.getIdProducto().equals(idProducto));
    }

    public void vaciarCarrito() {
        carrito.getItems().clear();
    }

    public int getCantidadTotal() {
        return carrito.getItems().stream().mapToInt(CarritoItem::getCantidad).sum();
    }

    public BigDecimal getTotal() {
        return carrito.getItems().stream()
                .map(item -> item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}