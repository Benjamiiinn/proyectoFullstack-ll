package com.levelup.proyectoFullstack_ll.repository;

import com.levelup.proyectoFullstack_ll.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByRut(String rut);
    boolean existsByUsername(String username);
    boolean existsByRut(String rut);
}
