package com.example.Proyecto_DAW.controller;

import com.example.Proyecto_DAW.entity.Categoria;
import com.example.Proyecto_DAW.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("pageTitle", "Categorías");
        return "categorias/listCategoria";
    }

    // FORMULARIO NUEVA
    @GetMapping("/new")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("pageTitle", "Nueva categoría");
        return "categorias/createCategoria";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("categoria") Categoria categoria,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Nueva categoría");
            return "categorias/createCategoria";
        }

        try {
            categoriaService.crearCategoria(categoria);
            return "redirect:/categorias";
        } catch (Exception e) {
            model.addAttribute("pageTitle", "Nueva categoría");
            model.addAttribute("errorMsg", e.getMessage());
            return "categorias/createCategoria";
        }
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
}
