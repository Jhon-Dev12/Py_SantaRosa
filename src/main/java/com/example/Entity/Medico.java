package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Medico")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(name = "Nro_Colegiatura", nullable = false, unique = true, length = 20)
    private String nroColegiatura;

    @Column(name = "Telefono", length = 15)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Especialidad_ID", nullable = false)
    private Especialidad especialidad;
}

