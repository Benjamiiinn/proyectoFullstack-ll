package com.levelup.proyectoFullstack_ll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Categoria;
import com.levelup.proyectoFullstack_ll.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria findById(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + id));
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria update(int id, Categoria categoriaDetails) {
        Categoria categoria = findById(id);
        categoria.setNombre(categoriaDetails.getNombre());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteById(int id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

}
