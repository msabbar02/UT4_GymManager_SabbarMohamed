package org.ut4.gymmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.repository.EjercicioRepository;

import java.util.List;

@Service
public class EjercicioService {
    @Autowired
    private final EjercicioRepository repository;

    public EjercicioService(EjercicioRepository repo) {
        this.repository = repo;
    }

    public List<Ejercicio> listarTodos() {
        return repository.findAll();
    }

    public Ejercicio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ejercicio> findByGrupoMuscular(String grupoMuscular) {
        String grupo = grupoMuscular.toLowerCase();
        return repository.findByGrupoMuscular_NombreIgnoreCase(grupo);
    }

    public Ejercicio guardar(Ejercicio ejercicio) {
        return repository.save(ejercicio);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
