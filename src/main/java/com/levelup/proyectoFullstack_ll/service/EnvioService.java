package com.levelup.proyectoFullstack_ll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Envio;
import com.levelup.proyectoFullstack_ll.repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    @Transactional
    public Envio findById(int id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado con ID: " + id));
    }

    @Transactional
    public Envio actualizarDireccion(int id, Envio envioDetails) {
        Envio envio = this.findById(id);

        envio.setDireccion(envioDetails.getDireccion());
        envio.setRegion(envioDetails.getRegion());
        envio.setComuna(envioDetails.getComuna());
        envio.setNombreContacto(envioDetails.getNombreContacto());
        envio.setTelefonoContacto(envioDetails.getTelefonoContacto());

        return envioRepository.save(envio);
    }

}
