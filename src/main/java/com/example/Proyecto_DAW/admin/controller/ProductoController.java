package com.example.Proyecto_DAW.admin.controller;

import com.example.Proyecto_DAW.admin.entity.Producto;
import com.example.Proyecto_DAW.admin.service.CategoriaService;
import com.example.Proyecto_DAW.admin.service.ProductoService;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProducts());
        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/create";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  @RequestParam("categoria.idCategoria") Long categoriaId,
                                  @RequestParam("archivoImagen") MultipartFile imagenFile) throws IOException {
        producto.setCategoria(categoriaService.obtenerPorId(categoriaId));
        if (!imagenFile.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + imagenFile.getOriginalFilename();
            Path ruta = Paths.get("uploads").resolve(nombreArchivo);
            // Crear la carpeta 'uploads' si no existe
            Path carpetaUploads = Paths.get("uploads");
            if (!Files.exists(carpetaUploads)) {
                Files.createDirectories(carpetaUploads);
            }
            Files.copy(imagenFile.getInputStream(), ruta);
            producto.setImagen(nombreArchivo);
        }
        productoService.createProduct(producto);
        return "redirect:/productos";
    }

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) throws MalformedURLException {
        Path ruta = Paths.get("uploads").resolve(filename);
        Resource recurso = new UrlResource(ruta.toUri());
        return ResponseEntity.ok().body(recurso);
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/edit";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Long id,
                                     @ModelAttribute Producto producto,
                                     @RequestParam("categoria.idCategoria") Long categoriaId) {
        producto.setIdProducto(id); // aseguramos que mantiene el mismo ID
        producto.setCategoria(categoriaService.obtenerPorId(categoriaId));
        productoService.updateProduct(producto);
        return "redirect:/productos";
    }

    @GetMapping("/ver/{id}")
    public String verProducto(@PathVariable Long id, Model model) {
        Producto producto = productoService.getProductById(id);
        model.addAttribute("producto", producto);
        return "productos/detail";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.deleteProduct(id);
        return "redirect:/productos";
    }
}
