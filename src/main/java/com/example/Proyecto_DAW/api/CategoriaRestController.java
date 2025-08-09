package com.example.Proyecto_DAW.api;

import com.example.Proyecto_DAW.dto.CategoriaResponseDTO;
import com.example.Proyecto_DAW.dto.ProductoResponseDTO;
import com.example.Proyecto_DAW.entity.Categoria;
import com.example.Proyecto_DAW.entity.Producto;
import com.example.Proyecto_DAW.service.CategoriaService;
import com.example.Proyecto_DAW.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:8082")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;

    // Listar todas las categorías usando CategoriaResponseDTO
    @GetMapping
    public List<CategoriaResponseDTO> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        List<CategoriaResponseDTO> categoriasDTO = new ArrayList<>();
        for (Categoria categoria : categorias) {
            CategoriaResponseDTO dto = new CategoriaResponseDTO();
//            dto.setId(categoria.getIdCategoria());
            dto.setNombre(categoria.getNombre());
            categoriasDTO.add(dto);
        }
        return categoriasDTO;
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<ProductoResponseDTO> listarProductosPorCategoria(@PathVariable Categoria categoria, Long idCategoria) {
        List<Producto> productos = productoService.getAllProducts();
        List<ProductoResponseDTO> productosDTO = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().getIdCategoria().equals(idCategoria)) {
                ProductoResponseDTO dto = new ProductoResponseDTO();
                dto.setId(producto.getIdProducto());
                dto.setNombre(producto.getNombre());
                dto.setDescripcion(producto.getDescripcion());
                dto.setPrecio(producto.getPrecio());
                dto.setStock(producto.getStock());
                dto.setImagen(producto.getImagen());
                dto.setId(categoria.getIdCategoria());
                dto.setIdCategoria(producto.getCategoria().getIdCategoria());
                dto.setNombreCategoria(producto.getCategoria().getNombre());
                productosDTO.add(dto);
            }
        }
        return productosDTO;
    }
}