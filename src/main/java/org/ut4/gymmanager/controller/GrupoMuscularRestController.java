package org.ut4.gymmanager.controller;

import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
public class GrupoMuscularRestController {

    private GrupoMuscularService service;

    public GrupoMuscularRestController(GrupoMuscularService service) {
        this.service = service;
    }

    // creat endPoints
    @GetMapping("/api/grupos")
    public List<GrupoMuscular> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/api/grupos/{id}")
    public GrupoMuscular buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/api/grupos")
    public GrupoMuscular guardar(@RequestBody GrupoMuscular grupoMuscular) {
        return service.guardar(grupoMuscular);
    }

    @DeleteMapping("/api/grupos/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
