package com.levelup.proyectoFullstack_ll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Plataforma;
import com.levelup.proyectoFullstack_ll.repository.PlataformaRepository;

import jakarta.transaction.Transactional;

@Service
public class PlataformaService {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Transactional
    public List<Plataforma> findAll() {
        return plataformaRepository.findAll();
    }

    @Transactional
    public Plataforma findById(int id) {
        return plataformaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Plataforma no encontrada con ID: " + id));
    }

    @Transactional
    public Plataforma save(Plataforma plataforma) {
        return plataformaRepository.save(plataforma);
    }

    @Transactional
    public Plataforma update(int id, Plataforma plataformaDetails) {
        Plataforma plataforma = findById(id);
        plataforma.setNombre(plataformaDetails.getNombre());
        return plataformaRepository.save(plataforma);
    }

    @Transactional
    public void deleteById(int id) {
        if (!plataformaRepository.existsById(id)) {
            throw new RuntimeException("Plataforma no encontrada");
        }
    }

}
