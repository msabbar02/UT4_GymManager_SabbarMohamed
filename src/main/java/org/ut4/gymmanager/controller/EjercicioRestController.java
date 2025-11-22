package org.ut4.gymmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.service.EjercicioService;

import java.util.List;
@RestController
public class EjercicioRestController {

    private EjercicioService service;

    public EjercicioRestController(EjercicioService service) {
        this.service = service;
    }

    @GetMapping("/api/ejercicios")
    public List<Ejercicio> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/api/ejercicios/{id}")
    public Ejercicio buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    @GetMapping(value = "/api/ejercicios" ,params = "grupo")
    public List<Ejercicio> findByGrupoMuscular(@RequestParam("grupo") String grupoMuscular) {
        return service.findByGrupoMuscular(grupoMuscular);
    }
}
