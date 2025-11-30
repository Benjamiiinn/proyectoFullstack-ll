package com.levelup.proyectoFullstack_ll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelup.proyectoFullstack_ll.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

}
