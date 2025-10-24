package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    List<Medico> findByApellidosContainingIgnoreCase(String apellidos);

    boolean existsByDni(String dni);
    boolean existsByNroColegiatura(String nroColegiatura);

    // Obtener el último número de colegiatura
    @Query("SELECT MAX(m.nroColegiatura) FROM Medico m")
    String findUltimoNroColegiatura();
}
