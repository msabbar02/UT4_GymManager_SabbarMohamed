package org.ut4.gymmanager.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.service.GrupoMuscularService;

import java.util.List;


@RestController
public class GrupoMuscularRestController {

    private final GrupoMuscularService grupoMuscularService;

    public GrupoMuscularRestController(GrupoMuscularService grupoMuscularService) {
        this.grupoMuscularService = grupoMuscularService;
    }

    // creat endPoints
    @GetMapping("/api/grupos")
    public ResponseEntity<List<GrupoMuscular>> listarTodos() {
        return ResponseEntity.ok(grupoMuscularService.listarTodos());
    }

    @GetMapping("/api/grupos/{id}")
    public ResponseEntity<GrupoMuscular> buscarPorId( @PathVariable Long id) {
        GrupoMuscular obj = grupoMuscularService.buscarPorId(id);
        if (obj != null) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/grupos")
    public ResponseEntity<GrupoMuscular> guardar(@Valid @RequestBody GrupoMuscular grupoMuscular) {
        GrupoMuscular grupo = grupoMuscularService.guardar(grupoMuscular);
        return new  ResponseEntity<>(grupo, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/grupos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (grupoMuscularService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        grupoMuscularService.borrar(id);
        return ResponseEntity.noContent().build();
    }
    // para modificar
    @PutMapping("/api/grupos/{id}")
    public ResponseEntity<GrupoMuscular> modificar(@PathVariable Long id , @Valid @RequestBody GrupoMuscular grupoMuscular) {
        GrupoMuscular actualizado = grupoMuscularService.editar(id, grupoMuscular);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
