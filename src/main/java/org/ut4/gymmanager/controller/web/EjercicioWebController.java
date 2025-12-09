package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.EjercicioService;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;

@Controller
@RequestMapping("/web/ejercicios")
public class EjercicioWebController {

    private EjercicioService ejercicioService;
    private GrupoMuscularService grupoMuscularService;
    // lista de dificultades
    List<String> dificultades =
            List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");

    public EjercicioWebController(EjercicioService service, GrupoMuscularService grupoMuscularService) {
        this.ejercicioService = service;
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping()
    public String ejercicios(Model model) {
        model.addAttribute("ejercicios", ejercicioService.listarTodos());
        return "ejercicios-list";
    }

    @GetMapping("/nuevo")
    public String nuevoEjercicio(Model model) {
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setGrupoMuscular(new GrupoMuscular());
        model.addAttribute("ejercicio", ejercicio);
        // pasar la lista de dificultades y grupos
        model.addAttribute("dificultades", dificultades);
        model.addAttribute("grupos", grupoMuscularService.listarTodos());
        return "ejercicio-form";
    }

    @PostMapping("/guardar")
    public String guardarEjercicio(@ModelAttribute Ejercicio ejercicio) {
        if (ejercicio.getId() == null) {
            ejercicioService.guardar(ejercicio);
        } else {
            ejercicioService.editar(ejercicio.getId(), ejercicio);
        }
        return "redirect:/web/ejercicios";
    }

    @GetMapping("/{id}/borrar")
    public String borrarEjercicio(@PathVariable Long id) {
        ejercicioService.eliminar(id);
        return "redirect:/web/ejercicios";
    }

    @GetMapping("/{id}/editar")
    public String editarEjercicio(@PathVariable Long id, Model model) {
        Ejercicio ejercicio = ejercicioService.buscarPorId(id);
        model.addAttribute("ejercicio", ejercicio);
        model.addAttribute("dificultades", dificultades);
        model.addAttribute("grupos", grupoMuscularService.listarTodos());
        return "ejercicio-form";
    }
}
