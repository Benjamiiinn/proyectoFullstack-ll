package com.levelup.proyectoFullstack_ll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.levelup.proyectoFullstack_ll.model.Usuario;
import com.levelup.proyectoFullstack_ll.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
            .orElseThrow();
    }

    @Transactional
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public Optional<Usuario> buscarPorRut(String rut) {
        return usuarioRepository.findByRut(rut);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(int id, Usuario usuarioDetails) {
        Usuario usuarioExistente = findById(id);

        if (usuarioDetails.getNombre() != null && !usuarioDetails.getNombre().isEmpty()) {
            usuarioExistente.setNombre(usuarioDetails.getNombre());
        }

        if (usuarioDetails.getUsername() != null && !usuarioDetails.getUsername().isEmpty()) {
            usuarioExistente.setUsername(usuarioDetails.getUsername());
        }
        if (usuarioDetails.getRut() != null && !usuarioDetails.getRut().isEmpty()) {
            usuarioExistente.setRut(usuarioDetails.getRut());   
        }

        usuarioExistente.setDireccion(usuarioDetails.getDireccion());
        usuarioExistente.setTelefono(usuarioDetails.getTelefono());

        if (usuarioDetails.getRol() != null) {
            usuarioExistente.setRol(usuarioDetails.getRol());
        }
        
        if (usuarioDetails.getPassword() != null && !usuarioDetails.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public Usuario toggleEstado(int id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        boolean estadoActual = usuario.isEnabled();
        usuario.setEnabled(!estadoActual);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteById(int id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEnabled(false);
        usuarioRepository.save(usuario);
    }
}
