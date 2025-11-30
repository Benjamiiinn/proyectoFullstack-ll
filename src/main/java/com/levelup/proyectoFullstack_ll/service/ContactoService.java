package com.levelup.proyectoFullstack_ll.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Contacto;
import com.levelup.proyectoFullstack_ll.repository.ContactoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Transactional
    public Contacto guardarMensaje(Contacto contacto) {
        contacto.setFechaEnvio(LocalDateTime.now());
        contacto.setLeido(false);
        return contactoRepository.save(contacto);
    }

    @Transactional
    public List<Contacto> listarTodos() {
        return contactoRepository.findAll();
    }
    @Transactional
    public Contacto findById(int id) {
        return contactoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
    }

    @Transactional
    public void deleteById(int id) {
        contactoRepository.deleteById(id);
    }

}
