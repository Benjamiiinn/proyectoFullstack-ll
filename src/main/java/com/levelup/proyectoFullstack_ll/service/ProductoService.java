package com.levelup.proyectoFullstack_ll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional
    public List<Producto> findByCategoria(String nombreCategoria) {
        return productoRepository.findByCategoriaNombre(nombreCategoria);
    }

    @Transactional
    public List<Producto> findDestacados() {
        return productoRepository.findByDestacadoTrue();
    }

    @Transactional
    public Optional<Producto> findById(int id)  {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public void descontarStock(int id, int cantidad) {
        Producto producto = this.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (producto.getStock() == null || producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }

    @Transactional
    public Producto actualizarProducto(int id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setImagen(productoDetails.getImagen());
        producto.setStock(productoDetails.getStock());
        producto.setDestacado(productoDetails.isDestacado());
        producto.setCategoria(productoDetails.getCategoria());
        producto.setPlataforma(productoDetails.getPlataforma());
        producto.setEspecificaciones(productoDetails.getEspecificaciones());

        return productoRepository.save(producto);
    }

    @Transactional
    public void deleteById(int id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El producto con ID " + id + " no existe");
        }
        productoRepository.deleteById(id);
    }
}
