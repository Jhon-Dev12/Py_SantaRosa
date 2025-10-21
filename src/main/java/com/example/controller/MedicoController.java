package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Entity.Medico;
import com.example.repository.EspecialidadRepository;
import com.example.Entity.Especialidad;
import com.example.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private EspecialidadRepository especialidadRepository;

    // LISTA
    @GetMapping("/lista")
    public String listarMedicos(Model model,
            @ModelAttribute("msg") String msg,
            @ModelAttribute("err") String err) {

        List<Medico> lista = medicoService.listarTodos();
        model.addAttribute("listaMedicos", lista);
        if (msg != null && !msg.isEmpty()) model.addAttribute("msg", msg);
        if (err != null && !err.isEmpty()) model.addAttribute("err", err);
        return "Admin/MedicosLista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevoMedico(Model model) {
        Medico medico = new Medico();
        medico.setEspecialidad(new Especialidad());
        medico.setNroColegiatura(medicoService.generarNroColegiatura());
        medico.setEspecialidad(new Especialidad());
        model.addAttribute("medico", medico);
        model.addAttribute("especialidades", especialidadRepository.findAll());
        return "Admin/MedicoForm";
    }

    // GUARDAR (nuevo o editar)
    @PostMapping("/guardar")
    public String guardarMedico(@ModelAttribute("medico") Medico medico, RedirectAttributes ra) {
        try {
            medicoService.guardar(medico);
            ra.addFlashAttribute("msg", "Médico guardado correctamente.");
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("err", ex.getMessage());
        }
        return "redirect:/medicos/lista";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editarMedico(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Medico medico = medicoService.buscarPorId(id).orElse(null);
        if (medico == null) {
            ra.addFlashAttribute("err", "Médico no encontrado.");
            return "redirect:/medicos/lista";
        }
        
        if (medico.getEspecialidad() == null) medico.setEspecialidad(new Especialidad());
        model.addAttribute("medico", medico);
        model.addAttribute("especialidades", especialidadRepository.findAll());
        return "Admin/MedicoForm";
    }

    // DETALLE
    @GetMapping("/detalle/{id}")
    public String detalleMedico(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        Medico medico = medicoService.buscarPorId(id).orElse(null);
        if (medico == null) {
            ra.addFlashAttribute("err", "Médico no encontrado.");
            return "redirect:/medicos/lista";
        }
        model.addAttribute("medico", medico);
        return "Admin/MedicoDetalle";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarMedico(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            boolean ok = medicoService.eliminar(id);
            if (ok) ra.addFlashAttribute("msg", "Médico eliminado correctamente.");
            else ra.addFlashAttribute("err", "Médico no encontrado.");
        } catch (Exception ex) {
            // probable FK constraint (Cita -> Medico)
            ra.addFlashAttribute("err", "No se pudo eliminar: el médico tiene citas u otras referencias.");
        }
        return "redirect:/medicos/lista";
    }
}
