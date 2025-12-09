package org.ut4.gymmanager.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;

@Controller
@RequestMapping("/web/grupos")
public class GrupoMuscularWebController {

    GrupoMuscularService grupoMuscularService;

    public GrupoMuscularWebController(GrupoMuscularService service) {
        this.grupoMuscularService = service;
    }
    @GetMapping()
    public String grupos(Model model){
        List<GrupoMuscular> grupos = grupoMuscularService.listarTodos();
        model.addAttribute("grupos", grupos);
        return "grupos-list";
    }

    @GetMapping("/nuevo")
    public String nuevoGrupo(Model model){
        model.addAttribute("grupo", new GrupoMuscular());
        return "grupos-form";
    }

    @PostMapping("/guardar")
    public String guardarGrupo(GrupoMuscular grupoMuscular){
       if (grupoMuscular.getId() == null){
           grupoMuscularService.guardar(grupoMuscular);
       } else{
           grupoMuscularService.editar(grupoMuscular.getId(), grupoMuscular);
       }
        return "redirect:/web/grupos";
    }

    @GetMapping("/{id}/borrar")
    public String borrarGrupo(@PathVariable Long id){
        grupoMuscularService.borrar(id);
        return "redirect:/web/grupos";
    }

    @GetMapping("/{id}/editar")
    public String editarGrupo(@PathVariable Long id, Model model){
        GrupoMuscular grupoMuscular = grupoMuscularService.buscarPorId(id);
        if (grupoMuscular != null){
            model.addAttribute("grupo", grupoMuscular);
            return "grupos-form";
        }else {
            return "redirect:/web/grupos";
        }
    }
}
