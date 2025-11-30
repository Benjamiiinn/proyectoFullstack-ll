package com.levelup.proyectoFullstack_ll.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.dto.PedidoRequest;
import com.levelup.proyectoFullstack_ll.model.DetallePedido;
import com.levelup.proyectoFullstack_ll.model.Envio;
import com.levelup.proyectoFullstack_ll.model.Pedido;
import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.model.Usuario;
import com.levelup.proyectoFullstack_ll.repository.PedidoRepository;
import com.levelup.proyectoFullstack_ll.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Pedido realizarCompra(PedidoRequest request) {
        
        Usuario usuario = usuarioService.findById(Math.toIntExact(request.getIdUsuario()));

        Envio envio = new Envio();
        envio.setNombreContacto(request.getNombre() + " " + request.getApellido());
        envio.setEmailContacto(request.getEmail());
        envio.setTelefonoContacto(request.getTelefono());
        envio.setDireccion(request.getDireccion());
        envio.setRegion(request.getRegion());
        envio.setComuna(request.getComuna());

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setEnvio(envio);

        List<DetallePedido> detalles = new ArrayList<>();
        int totalCalculado = 0;

        for (PedidoRequest.ProductoPedidoDto itemDto : request.getItems()) {
            Producto producto = productoRepository.findById(itemDto.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + itemDto.getIdProducto()));
            
            if(producto.getStock() < itemDto.getCantidad())
                throw new RuntimeException("Stock insuficiente para " + producto.getNombre());

            producto.setStock(producto.getStock() - itemDto.getCantidad());
            productoRepository.save(producto);
                
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.getCantidad());
            detalle.setPrecioUnitario(itemDto.getPrecio());

            detalles.add(detalle);
            totalCalculado += (detalle.getPrecioUnitario() * detalle.getCantidad());
        }

        pedido.setTotal(totalCalculado);
        pedido.setDetalles(detalles);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido findById(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    @Transactional
    public List<Pedido> listarPedidosPorUsuario(int id) {
        Usuario usuario = usuarioService.findById(id);
        return pedidoRepository.findByUsuario(usuario);
    }

    @Transactional
    public List<Pedido> buscarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return pedidoRepository.findByFechaBetween(inicio, fin);
    }

    @Transactional
    public Pedido actualizarEstado(int id, String nuevoEstado) {
        Pedido pedido = this.findById(id);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void deleteById(int id) {
        if(!pedidoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El pedido no existe");
        }
        pedidoRepository.deleteById(id);
    }

}
