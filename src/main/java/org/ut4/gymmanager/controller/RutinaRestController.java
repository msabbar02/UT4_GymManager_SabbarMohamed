package org.ut4.gymmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Rutina;
import org.ut4.gymmanager.service.RutinaService;

import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
public class RutinaRestController {

    private final RutinaService rutinaService;

    public RutinaRestController(RutinaService rutinaService) {
        this.rutinaService = rutinaService;
    }

    @GetMapping
    public ResponseEntity<List<Rutina>> listar() {
        return ResponseEntity.ok(rutinaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> detalle(@PathVariable Long id) {
        return rutinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rutina> crear(@RequestBody Rutina rutina) {
        if (rutina.getNombre() == null || rutina.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Rutina nueva = rutinaService.guardar(rutina);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> editar(@PathVariable Long id, @RequestBody Rutina rutina) {
        Rutina actualizada = rutinaService.editar(id, rutina);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        if (rutinaService.buscarPorId(id).isPresent()) {
            rutinaService.borrarPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}