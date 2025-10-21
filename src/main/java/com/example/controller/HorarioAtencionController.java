package com.example.controller;

import com.example.Entity.HorarioAtencion;
import com.example.Entity.Medico;
import com.example.Entity.DiaSemana;
import com.example.repository.MedicoRepository;
import com.example.service.HorarioAtencionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/horarios")
public class HorarioAtencionController {

    @Autowired
    private HorarioAtencionService horarioService;

    @Autowired
    private MedicoRepository medicoRepository;

    // LISTA
    @GetMapping("/lista")
    public String listarHorarios(Model model, 
                                 @ModelAttribute("msg") String msg,
                                 @ModelAttribute("err") String err) {
        List<HorarioAtencion> lista = horarioService.listarTodos();
        model.addAttribute("listaHorarios", lista);
        model.addAttribute("msg", msg);
        model.addAttribute("err", err);
        return "Admin/HorariosLista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevoHorario(Model model) {
        HorarioAtencion h = new HorarioAtencion();
        model.addAttribute("horario", h);
        model.addAttribute("medicos", medicoRepository.findAll());
        model.addAttribute("diasSemana", DiaSemana.values());
        return "Admin/HorarioForm";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("horario") HorarioAtencion h, RedirectAttributes ra) {
        horarioService.guardar(h);
        ra.addFlashAttribute("msg", "Horario guardado correctamente.");
        return "redirect:/horarios/lista";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        boolean ok = horarioService.eliminar(id);
        if (ok) ra.addFlashAttribute("msg", "Horario eliminado correctamente.");
        else ra.addFlashAttribute("err", "No se encontró el horario.");
        return "redirect:/horarios/lista";
    }
}
