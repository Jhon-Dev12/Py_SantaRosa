package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Especialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}