package com.levelup.proyectoFullstack_ll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelup.proyectoFullstack_ll.model.Especificacion;

@Repository
public interface EspecificacionRepository extends JpaRepository<Especificacion, Integer>{
    List<Especificacion> findByProductoId(int id);
}
