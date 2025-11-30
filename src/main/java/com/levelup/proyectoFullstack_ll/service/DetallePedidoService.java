package com.levelup.proyectoFullstack_ll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.DetallePedido;
import com.levelup.proyectoFullstack_ll.repository.DetallePedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public List<DetallePedido> findByPedido(int id) {
        return detallePedidoRepository.findByPedidoId(id);
    }

    @Transactional
    public DetallePedido findById(int id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado con ID: " + id));
    }

    @Transactional
    public void deleteById(int id) {
        detallePedidoRepository.deleteById(id);
    }

}
