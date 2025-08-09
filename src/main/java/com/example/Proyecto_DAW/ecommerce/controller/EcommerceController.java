package com.example.Proyecto_DAW.ecommerce.controller;

import com.example.Proyecto_DAW.admin.entity.Producto;
import com.example.Proyecto_DAW.admin.service.CarritoService;
import com.example.Proyecto_DAW.admin.service.CategoriaService;
import com.example.Proyecto_DAW.admin.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ecommerce")
public class EcommerceController {

    @GetMapping("/")
    public String ecommerce(){
        return "/ecommerce/listProducts.html";
    }

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/usar-producto")
    public String usarMetodoProducto() {
        return productoService.metodoDeProducto();
    }

    @GetMapping("/productos/categoria/{idCategoria}")
    public String listarPorCategoria(@PathVariable Long idCategoria, Model model) {
        model.addAttribute("productos", productoService.getProductsByCategoriaId(idCategoria));
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaSeleccionada", idCategoria);
        return "ecommerce/listProducts";
    }

    @GetMapping("/vista-productos")
    public String mostrarVistaProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProducts());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "ecommerce/listProducts";
    }

    @GetMapping("/productos")
    public String listarTodos(Model model) {
        model.addAttribute("productos", productoService.getAllProducts());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaSeleccionada", null);
        return "ecommerce/listProducts";
    }

    // Agregar producto al carrito
    @PostMapping("/carrito/agregar/{idProducto}")
    public String agregarAlCarrito(@PathVariable Long idProducto, @RequestParam(defaultValue = "1") int cantidad, Model model) {
        carritoService.agregarProducto(idProducto, cantidad);
        // Agrega los datos del carrito al modelo
        model.addAttribute("productos", productoService.getAllProducts());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaSeleccionada", null);
        model.addAttribute("items", carritoService.getCarrito().getItems());
        model.addAttribute("total", carritoService.getTotal());
        model.addAttribute("cantidadCarrito", carritoService.getCantidadTotal());
        model.addAttribute("totalConEnvio", carritoService.getTotal().add(new java.math.BigDecimal("10.00")));
        return "ecommerce/listProducts";
    }

    // Quitar producto del carrito
    @PostMapping("/carrito/quitar/{idProducto}")
    public String quitarDelCarrito(@PathVariable Long idProducto, Model model) {
        carritoService.quitarProducto(idProducto);
        // Agrega los datos del carrito al modelo
        model.addAttribute("productos", productoService.getAllProducts());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("categoriaSeleccionada", null);
        model.addAttribute("items", carritoService.getCarrito().getItems());
        model.addAttribute("total", carritoService.getTotal());
        model.addAttribute("cantidadCarrito", carritoService.getCantidadTotal());
        model.addAttribute("totalConEnvio", carritoService.getTotal().add(new java.math.BigDecimal("10.00")));
        return "ecommerce/listProducts";
    }

    // Mostrar el carrito
    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        model.addAttribute("carrito", carritoService.getCarrito());
        model.addAttribute("items", carritoService.getCarrito().getItems());
        model.addAttribute("total", carritoService.getTotal());
        model.addAttribute("cantidadCarrito", carritoService.getCantidadTotal());
        return "ecommerce/carrito";
    }


}