package com.example.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entity.Roles;
import com.example.Entity.Usuario;
import com.example.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

 // UsuarioService.java
    public Usuario registrarUsuario(String username, String password, Roles rol) {
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setContrasenia(passwordEncoder.encode(password));
        u.setRol(rol);
        return usuarioRepository.save(u);
    }


    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}