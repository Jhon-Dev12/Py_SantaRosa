package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.Entity.Usuario;
import com.example.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Página de inicio
    @GetMapping("/")
    public String home() {
        return "index"; // index.html
    }

    // Formulario de registro
    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // registro.html
    }

    // Página de login
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    // Página para administrador
    @GetMapping("/admin/home")
    public String admin() {
        return "Admin/admin"; // admin.html
    }

    // Página para médicos
    @GetMapping("/rcpsta/home")
    public String recepcionista() {
        return "Recepcionista/recepcionista"; // recpecionista.html
    }
    
    
    @GetMapping("/cajero/home")
    public String cajero () {
        return "Cajero/cajero"; // cajero.html
    }
    


    
}
