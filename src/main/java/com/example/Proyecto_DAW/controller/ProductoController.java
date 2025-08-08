package com.example.Proyecto_DAW.controller;

import com.example.Proyecto_DAW.entity.Producto;
import com.example.Proyecto_DAW.service.CategoriaService;
import com.example.Proyecto_DAW.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardarProducto(@ModelAttribute Producto producto, @RequestParam("categoria.idCategoria") Long categoriaId) {
        producto.setCategoria(categoriaService.obtenerPorId(categoriaId));
        productoService.createProduct(producto);
        return "redirect:/productos";
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
