package com.levelup.proyectoFullstack_ll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelup.proyectoFullstack_ll.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriaNombre(String nombre);
    List<Producto> findByDestacadoTrue();
    List<Producto> findByPlataformaNombre(String nombre);
}
