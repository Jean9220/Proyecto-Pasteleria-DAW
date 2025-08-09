package com.example.Proyecto_DAW.admin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "email")
    private String email;
    @Column(name = "clave")
    private String Clave;
    @Column(name = "reestablecer")
    private Boolean reestablecer;

    private Estado estado;

    public enum Estado {
        ACTIVO, INACTIVO
    }

    public Usuario() {
    }

    public Usuario(Long idUsuario, Estado estado, Boolean reestablecer, String clave, String correo, String apellidos, String nombres) {
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.reestablecer = reestablecer;
        Clave = clave;
        email = email;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Boolean getReestablecer() {
        return reestablecer;
    }

    public void setReestablecer(Boolean reestablecer) {
        this.reestablecer = reestablecer;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
