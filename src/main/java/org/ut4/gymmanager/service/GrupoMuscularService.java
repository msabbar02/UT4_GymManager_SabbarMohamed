package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.GrupoMuscular;
import org.ut4.gymmanager.repository.GrupoMuscularRepository;

import java.util.List;

@Service
public class GrupoMuscularService {

    private GrupoMuscularRepository repository;

    public GrupoMuscularService(GrupoMuscularRepository repository) {
        this.repository = repository;
    }

    public List<GrupoMuscular> listarTodos() {
        return repository.findAll();
    }

    public GrupoMuscular buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public GrupoMuscular guardar(GrupoMuscular grupoMuscular){
        return repository.save(grupoMuscular);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
// he añadido metodo modificar en caso queremos modificar algún grupoMuscular
    public GrupoMuscular editar(Long id, GrupoMuscular grupoMuscular){
        GrupoMuscular auxGrupoMuscular = repository.findById(id).orElse(null);
        if (auxGrupoMuscular != null){
            auxGrupoMuscular.setNombre(grupoMuscular.getNombre());
            auxGrupoMuscular.setDescripcion(grupoMuscular.getDescripcion());
            return repository.save(auxGrupoMuscular);
        }
        return null;
    }

}
