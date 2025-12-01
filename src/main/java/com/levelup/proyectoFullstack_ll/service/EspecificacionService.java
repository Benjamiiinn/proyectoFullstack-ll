package com.levelup.proyectoFullstack_ll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Especificacion;
import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.repository.EspecificacionRepository;
import com.levelup.proyectoFullstack_ll.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class EspecificacionService {

    @Autowired
    private EspecificacionRepository especificacionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public List<Especificacion> findByProducto(int id) {
        return especificacionRepository.findByProductoId(id);
    }

    @Transactional
    public Especificacion save(Especificacion especificacion) {
        if (especificacion.getProducto() != null && especificacion.getProducto().getId() != null) {
            Producto productoReal = productoRepository.findById(especificacion.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            especificacion.setProducto(productoReal);
        }
        return especificacionRepository.save(especificacion);
    }

    @Transactional
    public Especificacion update(int id, Especificacion especificacionDetails) {
        Especificacion especificacion = especificacionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Especifiacion no encontrada con ID: " + id));
        especificacion.setNombre(especificacionDetails.getNombre());
        especificacion.setValorEspecifico(especificacionDetails.getValorEspecifico());

        if (especificacionDetails.getProducto() != null) {
            Producto p = productoRepository.findById(especificacionDetails.getProducto().getId()).orElseThrow();
            especificacion.setProducto(p);
        }

        return especificacionRepository.save(especificacion);
    }

    @Transactional
    public void deleteById(int id) {
        especificacionRepository.deleteById(id);
    }

}
