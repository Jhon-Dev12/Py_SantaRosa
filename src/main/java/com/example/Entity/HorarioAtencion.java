package com.example.Entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Horarios_Atencion",
       uniqueConstraints = @UniqueConstraint(columnNames = {"ID_Medico", "Dia_Semana", "Horario_Entrada", "Horario_Salida"}))
public class HorarioAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Horario")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Medico", nullable = false)
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @Column(name = "Dia_Semana", nullable = false)
    private DiaSemana diaSemana;

    @Column(name = "Horario_Entrada", nullable = false)
    private LocalTime horarioEntrada;

    @Column(name = "Horario_Salida", nullable = false)
    private LocalTime horarioSalida;
    
    
 // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }

    public LocalTime getHorarioEntrada() { return horarioEntrada; }
    public void setHorarioEntrada(LocalTime horarioEntrada) { this.horarioEntrada = horarioEntrada; }

    public LocalTime getHorarioSalida() { return horarioSalida; }
    public void setHorarioSalida(LocalTime horarioSalida) { this.horarioSalida = horarioSalida; }
}