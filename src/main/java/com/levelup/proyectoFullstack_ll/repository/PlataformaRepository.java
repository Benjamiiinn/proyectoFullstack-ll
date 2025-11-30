package com.levelup.proyectoFullstack_ll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelup.proyectoFullstack_ll.model.Plataforma;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Integer> {

}
