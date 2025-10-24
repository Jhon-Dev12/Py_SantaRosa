package com.example.repository;

import com.example.Entity.HorarioAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Integer> {
    List<HorarioAtencion> findByMedico_Id(Integer idMedico);
}