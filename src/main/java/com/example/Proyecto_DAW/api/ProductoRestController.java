package com.example.Proyecto_DAW.api;

import com.example.Proyecto_DAW.dto.ProductoDetalleResponseDTO;
import com.example.Proyecto_DAW.dto.ProductoResponseDTO;
import com.example.Proyecto_DAW.entity.Producto;
import com.example.Proyecto_DAW.repository.ProductoRepository;
import com.example.Proyecto_DAW.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:8082")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    // Listar productos usando ProductoResponseDTO
    @GetMapping
    public List<ProductoResponseDTO> listarProductos() {
        List<Producto> productos = productoService.getAllProducts();
        List<ProductoResponseDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoResponseDTO dto = new ProductoResponseDTO();
            dto.setId(producto.getIdProducto());
            dto.setNombre(producto.getNombre());
            dto.setDescripcion(producto.getDescripcion());
            dto.setPrecio(producto.getPrecio());
            dto.setStock(producto.getStock());
            dto.setImagen(producto.getImagen()); // Ajusta según tu entidad
            dto.setIdCategoria(producto.getCategoria().getIdCategoria());
            dto.setNombreCategoria(producto.getCategoria().getNombre());
            productosDTO.add(dto);
        }
        return productosDTO;
    }

    // Obtener detalle de producto usando ProductoDetalleResponseDTO
    @GetMapping("/{id}")
    public ProductoDetalleResponseDTO obtenerProducto(@PathVariable Long id) {
        Producto producto = productoService.getProductById(id);
        ProductoDetalleResponseDTO dto = new ProductoDetalleResponseDTO();
        dto.setId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setImagenUrl(producto.getImagen());
        dto.setIdCategoria(producto.getCategoria().getIdCategoria());
        dto.setNombreCategoria(producto.getCategoria().getNombre());
        return dto;
    }
}