package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.repository.EjercicioRepository;

import java.util.List;

@Service
public class EjercicioService {

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

    public List<Ejercicio> buscarPorGrupoMuscular(String grupoMuscular) {
        return repository.findByGrupoMuscular_NombreIgnoreCase(grupoMuscular);
    }



    public List<Ejercicio> filtrarPorDificultad(String dificultad) {
        return repository.findByDificultad_IgnoreCase((dificultad));
    }


    public Ejercicio guardar(Ejercicio ejercicio) {
        if (validarDificultad(ejercicio)) {
            ejercicio.setDificultad(ejercicio.getDificultad().toUpperCase());
            return repository.save(ejercicio);
        } else {
            return null;
        }
    }

    public Ejercicio editar(Long id, Ejercicio ejercicio) {

        if (!validarDificultad(ejercicio)) {
            return null;
        }


        Ejercicio auxEjercicio = repository.findById(id).orElse(null);

        if (auxEjercicio != null) {
            auxEjercicio.setNombre(ejercicio.getNombre());
            auxEjercicio.setDescripcion(ejercicio.getDescripcion());
            auxEjercicio.setDificultad(ejercicio.getDificultad().toUpperCase());
            auxEjercicio.setGrupoMuscular(ejercicio.getGrupoMuscular());

            return repository.save(auxEjercicio);
        }

        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Ejercicio> buscarConFiltros(String grupo,String dificultad){
        if(grupo != null && dificultad != null){
            return repository.findByGrupoMuscular_NombreIgnoreCaseAndDificultadIgnoreCase(grupo,dificultad);
        }
        if(grupo != null ){
            return repository.findByGrupoMuscular_NombreIgnoreCase(grupo);
        }
        if(dificultad != null){
            return repository.findByDificultad_IgnoreCase(dificultad);
        }
        return repository.findAll();
    }



    public boolean validarDificultad(Ejercicio ejercicio) {
        if (ejercicio.getDificultad() == null) {
            return false;
        }

        String dificultad = ejercicio.getDificultad().toUpperCase();
        return dificultad.equals("AVANZADO") ||
                dificultad.equals("INTERMEDIO") ||
                dificultad.equals("PRINCIPIANTE");
    }
}