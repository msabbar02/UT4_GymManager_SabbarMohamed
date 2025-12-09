package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Rutina;
import org.ut4.gymmanager.model.RutinaEjercicio;
import org.ut4.gymmanager.service.EjercicioService;
import org.ut4.gymmanager.service.RutinaService;

import java.util.List;

@Controller
@RequestMapping("/web/rutinas")
public class RutinaWebController {

    private final RutinaService rutinaService;
    private final EjercicioService ejercicioService;

    private final List<String> dificultades = List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");

    public RutinaWebController(RutinaService rutinaService, EjercicioService ejercicioService) {
        this.rutinaService = rutinaService;
        this.ejercicioService = ejercicioService;
    }


    @GetMapping
    public String rutinas(Model model) {
        model.addAttribute("rutinas", rutinaService.listarTodos());
        return "rutinas-list";
    }

    @GetMapping("/nueva")
    public String nuevaRutina(Model model) {
        Rutina rutina = new Rutina();
        model.addAttribute("rutina", rutina);
        model.addAttribute("dificultades", dificultades);
        return "rutina-form";
    }

    @PostMapping("/guardar")
    public String guardarRutina(@ModelAttribute Rutina rutina) {
        if (rutina.getId() == null) {
            rutinaService.guardar(rutina);
        } else {
            rutinaService.editar(rutina.getId(), rutina);
        }
        return "redirect:/web/rutinas";
    }

    @GetMapping("/{id}/editar")
    public String editarRutina(@PathVariable Long id, Model model) {
        Rutina rutina = rutinaService.buscarPorId(id).orElse(null);
        if (rutina == null) return "redirect:/web/rutinas";

        model.addAttribute("rutina", rutina);
        model.addAttribute("dificultades", dificultades);
        return "rutina-form";
    }


    @GetMapping("/{id}/borrar")
    public String borrarRutina(@PathVariable Long id) {
        rutinaService.borrarPorId(id);
        return "redirect:/web/rutinas";
    }

    // --- GESTIÓN DE DETALLE Y EJERCICIOS ---
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Rutina rutina = rutinaService.buscarPorId(id).orElse(null);
        if (rutina == null) return "redirect:/web/rutinas";

        model.addAttribute("rutina", rutina);
        return "rutina-detalle";
    }

    // FORMULARIO AÑADIR EJERCICIO A RUTINA
    @GetMapping("/{id}/ejercicios/nuevo")
    public String formAgregarEjercicio(@PathVariable Long id, Model model) {
        Rutina rutina = rutinaService.buscarPorId(id).orElse(null);
        if (rutina == null) return "redirect:/web/rutinas";

        RutinaEjercicio rutinaEjercicio = new RutinaEjercicio();
        rutinaEjercicio.setRutina(rutina);

        model.addAttribute("rutinaEjercicio", rutinaEjercicio);
        model.addAttribute("rutinaId", id);
        model.addAttribute("listaEjercicios", ejercicioService.listarTodos()); // Para el <select>

        return "rutina-ejercicio-form";
    }

    // GUARDAR EJERCICIO EN RUTINA
    @PostMapping("/{id}/ejercicios/guardar")
    public String guardarEjercicioEnRutina(@PathVariable("id") Long rutinaId, @ModelAttribute RutinaEjercicio rutinaEjercicio) {
        rutinaEjercicio.setId(null);
        Rutina rutina = rutinaService.buscarPorId(rutinaId).orElse(null);

        if (rutina != null) {
            rutinaEjercicio.setRutina(rutina);
            rutina.getRutinaEjercicios().add(rutinaEjercicio);
            rutinaService.guardar(rutina);
        }

        return STR."redirect:/web/rutinas/\{rutinaId}";
    }

    // BORRAR EJERCICIO DE RUTINA
    @GetMapping("/{rutinaId}/ejercicios/borrar/{rutinaEjercicioId}")
    public String borrarEjercicioDeRutina(@PathVariable Long rutinaId, @PathVariable Long rutinaEjercicioId) {
        rutinaService.borrarEjercicioRutina(rutinaEjercicioId);
        return STR."redirect:/web/rutinas/\{rutinaId}";
    }
}