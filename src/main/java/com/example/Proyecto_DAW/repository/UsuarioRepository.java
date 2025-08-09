package com.example.Proyecto_DAW.repository;

import com.example.Proyecto_DAW.entity.Categoria;
import com.example.Proyecto_DAW.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
