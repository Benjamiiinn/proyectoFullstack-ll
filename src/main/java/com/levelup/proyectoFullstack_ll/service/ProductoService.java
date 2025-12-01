package com.levelup.proyectoFullstack_ll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Categoria;
import com.levelup.proyectoFullstack_ll.model.Plataforma;
import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.repository.CategoriaRepository;
import com.levelup.proyectoFullstack_ll.repository.PlataformaRepository;
import com.levelup.proyectoFullstack_ll.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;


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
    public Producto findById(int id)  {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: "+ id));
    }

    @Transactional
    public Producto save(Producto producto) {
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            Categoria cat = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
            producto.setCategoria(cat);
        }

        if(producto.getPlataforma() != null && producto.getPlataforma().getId() != null) {
            Plataforma plat = plataformaRepository.findById(producto.getPlataforma().getId())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada"));
            producto.setPlataforma(plat);
        }       
        return productoRepository.save(producto);
    }

    @Transactional
    public void descontarStock(int id, int cantidad) {
        Producto producto = this.findById(id);

        if (producto.getStock() == null || producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }

    @Transactional
    public Producto actualizarProducto(int id, Producto productoDetails) {
        Producto producto = this.findById(id);

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setImagen(productoDetails.getImagen());
        producto.setStock(productoDetails.getStock());
        producto.setDestacado(productoDetails.isDestacado());
        
        if (productoDetails.getCategoria() != null) {
            Categoria cat = categoriaRepository.findById(productoDetails.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            producto.setCategoria(cat);
        }

        if (productoDetails.getPlataforma() != null) {
            Plataforma plat = plataformaRepository.findById(productoDetails.getPlataforma().getId())
                .orElseThrow(() -> new RuntimeException("Plataforma no encontrada"));
            producto.setPlataforma(plat);
        }

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
