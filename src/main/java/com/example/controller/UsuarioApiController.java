package com.example.controller;

import org.springframework.web.bind.annotation.*;
import com.example.service.UsuarioService;
import com.example.Entity.Roles;
import com.example.Entity.Usuario;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiController {

    private final UsuarioService usuarioService;

    public UsuarioApiController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar usuario
    @PostMapping("/registro")
    public Usuario registrar(@RequestBody Usuario request) {
        Roles rolEnum = Roles.valueOf(request.getRol().name()); // si request.getRol() es enum
        return usuarioService.registrarUsuario(
            request.getUsername(),
            request.getPassword(),
            rolEnum
        );
    }


    // Listar todos los usuarios
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }
}





