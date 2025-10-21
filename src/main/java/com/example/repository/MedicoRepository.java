package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.Entity.Medico;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    List<Medico> findByApellidosContainingIgnoreCase(String apellidos);

    boolean existsByDni(String dni);
    boolean existsByNroColegiatura(String nroColegiatura);

    // Obtener el último número de colegiatura
    @Query("SELECT MAX(m.nroColegiatura) FROM Medico m")
    String findUltimoNroColegiatura();
}