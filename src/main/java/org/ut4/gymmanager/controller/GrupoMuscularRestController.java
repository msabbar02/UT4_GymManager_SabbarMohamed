package org.ut4.gymmanager.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GrupoMuscular>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/api/grupos/{id}")
    public ResponseEntity<GrupoMuscular> buscarPorId( @PathVariable Long id) {
        GrupoMuscular obj = service.buscarPorId(id);
        if (obj != null) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/grupos")
    public ResponseEntity<GrupoMuscular> guardar(@Valid @RequestBody GrupoMuscular grupoMuscular) {
        GrupoMuscular grupo = service.guardar(grupoMuscular);
        return new  ResponseEntity<>(grupo, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/grupos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // para modificar
    @PutMapping("/api/grupos/{id}")
    public ResponseEntity<GrupoMuscular> modificar(@PathVariable Long id , @Valid @RequestBody GrupoMuscular grupoMuscular) {
        GrupoMuscular actualizado = service.editar(id, grupoMuscular);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
