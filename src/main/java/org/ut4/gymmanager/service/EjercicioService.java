package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.repository.EjercicioRepositoryFake;

import java.util.List;

@Service
public class EjercicioService {

    private final EjercicioRepositoryFake repository;

    public EjercicioService(EjercicioRepositoryFake repo) {
        this.repository = repo;
    }

    public List<Ejercicio> listarTodos() {
        return repository.findAll();
    }

    public Ejercicio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ejercicio> findByGrupoMuscular(String grupoMuscular) {
        return repository.findByGrupoMuscular(grupoMuscular);
    }
}
