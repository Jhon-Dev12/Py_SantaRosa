package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "hola"; // la contraseña que quieras
        String hash = encoder.encode(password);
        System.out.println(hash);
    }
}
