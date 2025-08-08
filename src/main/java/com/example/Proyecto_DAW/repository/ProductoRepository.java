package com.example.Proyecto_DAW.repository;

import com.example.Proyecto_DAW.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {


}
