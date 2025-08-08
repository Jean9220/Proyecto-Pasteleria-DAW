package com.example.Proyecto_DAW.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdUsuario;
    private String Nombres;
    private String Apellidos;
    private String Correo;
    private String Clave;
    private Boolean Reestablecer;
    private Boolean Activo;
}
