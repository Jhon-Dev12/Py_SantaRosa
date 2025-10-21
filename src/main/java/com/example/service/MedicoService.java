package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.MedicoRepository;
import com.example.repository.EspecialidadRepository;
import com.example.Entity.Medico;
import com.example.Entity.Especialidad;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    public Medico guardar(Medico medico) {
        // 🔹 Validar duplicado de DNI
        if (medico.getDni() != null && medicoRepository.existsByDni(medico.getDni())) {
            throw new RuntimeException("El DNI ya está registrado para otro médico.");
        }

        // 🔹 Validar duplicado de NroColegiatura
        if (medico.getNroColegiatura() != null && medicoRepository.existsByNroColegiatura(medico.getNroColegiatura())) {
            throw new RuntimeException("El número de colegiatura ya está registrado para otro médico.");
        }

        // 🔹 Resolver especialidad (por ID)
        if (medico.getEspecialidad() != null && medico.getEspecialidad().getId() != null) {
            Especialidad e = especialidadRepository.findById(medico.getEspecialidad().getId()).orElse(null);
            medico.setEspecialidad(e);
        }

        return medicoRepository.save(medico);
    }

    public Medico actualizar(Integer id, Medico datos) {
        return medicoRepository.findById(id).map(m -> {
            m.setNombres(datos.getNombres());
            m.setApellidos(datos.getApellidos());
            m.setDni(datos.getDni());
            m.setNroColegiatura(datos.getNroColegiatura());
            m.setTelefono(datos.getTelefono());
            if (datos.getEspecialidad() != null && datos.getEspecialidad().getId() != null) {
                Especialidad e = especialidadRepository.findById(datos.getEspecialidad().getId()).orElse(null);
                m.setEspecialidad(e);
            }
            return medicoRepository.save(m);
        }).orElse(null);
    }

    public boolean eliminar(Integer id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Medico> buscarPorApellidos(String apellidos) {
        return medicoRepository.findByApellidosContainingIgnoreCase(apellidos);
    }

    // 🔹 Generar número de colegiatura incremental
    public String generarNroColegiatura() {
        String ultimo = medicoRepository.findUltimoNroColegiatura();
        if (ultimo == null || ultimo.isEmpty()) {
            return "COL-1001";
        }

        try {
            int num = Integer.parseInt(ultimo.replace("COL-", "")) + 1;
            return "COL-" + num;
        } catch (Exception e) {
            // Si hay formato raro, reinicia desde 1001
            return "COL-1001";
        }
    }
}