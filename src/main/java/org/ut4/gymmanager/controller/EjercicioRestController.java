package org.ut4.gymmanager.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.service.EjercicioService;

import java.util.List;

@RestController

public class EjercicioRestController {

    private final EjercicioService ejercicioService;

    public EjercicioRestController(EjercicioService ejercicioService) {
        this.ejercicioService = ejercicioService;
    }

    // --- MÉTODO UNIFICADO (Sustituye a listarTodos, findByGrupo, findByDificultad...) ---
    // Este método maneja:
    // 1. /api/ejercicios (Sin filtros -> devuelve todos)
    // 2. /api/ejercicios?grupo=Pecho (Solo grupo)
    // 3. /api/ejercicios?dificultad=Alta (Solo dificultad)
    // 4. /api/ejercicios?grupo=Pecho&dificultad=Alta (Ambos)
    @GetMapping("/api/ejercicios")
    public ResponseEntity<List<Ejercicio>> buscarEjercicios(@RequestParam(required = false) String grupo, @RequestParam(required = false) String dificultad){
        List<Ejercicio> resultados = ejercicioService.buscarConFiltros(grupo, dificultad);
        if(resultados.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @GetMapping("/api/ejercicios/{id}")
    public ResponseEntity<Ejercicio> buscarPorId(@PathVariable Long id) {
        Ejercicio auxEjercicio = ejercicioService.buscarPorId(id);
        if (auxEjercicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(auxEjercicio);
    }

    @PostMapping("/api/ejercicios")
    public ResponseEntity<Ejercicio> guardar(@Valid @RequestBody Ejercicio ejercicio) {
        Ejercicio auxEjercicio = ejercicioService.guardar(ejercicio);
        // Si el servicio devuelve null (por la validación de dificultad), devolvemos 400 Bad Request
        if (auxEjercicio == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(auxEjercicio, HttpStatus.CREATED);
    }

    @PutMapping("/api/ejercicios/{id}")
    public ResponseEntity<Ejercicio> modificar(@Valid @RequestBody Ejercicio ejercicio, @PathVariable Long id) {
        Ejercicio actualizado = ejercicioService.editar(id, ejercicio);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/api/ejercicios/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (ejercicioService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        ejercicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}