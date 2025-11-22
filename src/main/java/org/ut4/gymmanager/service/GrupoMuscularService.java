package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.repository.GrupoMuscularRepositoryFake;

import java.util.List;

@Service
public class GrupoMuscularService {

    private GrupoMuscularRepositoryFake repository;

    public GrupoMuscularService(GrupoMuscularRepositoryFake repository) {
        this.repository = repository;
    }

    public List<GrupoMuscular> listarTodos() {
        return repository.findAll();
    }

    public GrupoMuscular buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

}
