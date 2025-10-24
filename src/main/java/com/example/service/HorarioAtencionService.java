package com.example.service;

import com.example.Entity.HorarioAtencion;
import com.example.repository.HorarioAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HorarioAtencionService {

    @Autowired
    private HorarioAtencionRepository horarioRepo;

    public List<HorarioAtencion> listarTodos() {
        return horarioRepo.findAll();
    }

    public List<HorarioAtencion> listarPorMedico(Integer idMedico) {
        return horarioRepo.findByMedico_Id(idMedico);
    }

    public HorarioAtencion guardar(HorarioAtencion h) {
        return horarioRepo.save(h);
    }

    public HorarioAtencion buscarPorId(Integer id) {
        return horarioRepo.findById(id).orElse(null);
    }

    public boolean eliminar(Integer id) {
        if (horarioRepo.existsById(id)) {
            horarioRepo.deleteById(id);
            return true;
        }
        return false;
    }
}