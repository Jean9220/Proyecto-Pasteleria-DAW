package com.example.Proyecto_DAW.admin.service;

import com.example.Proyecto_DAW.admin.entity.Producto;
import com.example.Proyecto_DAW.admin.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Component
public class ProductoService {

    @Autowired
    private ProductoRepository productRepository;

    public String metodoDeProducto() {
        return "Producto ejecutado";
    }

    public Producto createProduct(Producto product) {
        if (product.getCategoria() == null || product.getCategoria().getIdCategoria() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        if (product.getNombre() == null || product.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getDescripcion() == null || product.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion del producto es obligatoria");
        }
        if (product.getPrecio() == null || product.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (product.getStock() == null || product.getStock() <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        return productRepository.save(product);
    }

    public List<Producto> getAllProducts() {
        return (List<Producto>) productRepository.findAll();
    }

    public List<Producto> getProductsByCategoriaId(Long categoriaId) {
        return productRepository.findByCategoria_IdCategoria(categoriaId);
    }

    public Producto getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));
    }

    public Producto updateProduct(Producto producto) {
        return productRepository.save(producto);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productRepository.deleteById(id);
    }
}
