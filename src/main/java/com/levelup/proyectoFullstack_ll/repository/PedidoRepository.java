package com.levelup.proyectoFullstack_ll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levelup.proyectoFullstack_ll.model.Pedido;
import com.levelup.proyectoFullstack_ll.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
