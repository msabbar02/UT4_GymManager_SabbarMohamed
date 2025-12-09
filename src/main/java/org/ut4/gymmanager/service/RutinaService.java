package org.ut4.gymmanager.service;

import org.springframework.stereotype.Service;
import org.ut4.gymmanager.model.Rutina;
import org.ut4.gymmanager.model.RutinaEjercicio;
import org.ut4.gymmanager.repository.RutinaEjercicioRepository;
import org.ut4.gymmanager.repository.RutinaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RutinaService {

    private final RutinaRepository rutinaRepository;
    private final RutinaEjercicioRepository rutinaEjercicioRepository;

    public RutinaService(RutinaRepository rutinaRepository, RutinaEjercicioRepository rutinaEjercicioRepository) {
        this.rutinaRepository = rutinaRepository;
        this.rutinaEjercicioRepository = rutinaEjercicioRepository;
    }

    public List<Rutina> listarTodos() {
        return rutinaRepository.findAll();
    }

    public Optional<Rutina> buscarPorId(Long id) {
        return rutinaRepository.findById(id);
    }

    public Rutina guardar(Rutina rutina) {
        return rutinaRepository.save(rutina);
    }

    public void borrarPorId(Long id) {
        rutinaRepository.deleteById(id);
    }


    public void guardarEjercicioRutina(RutinaEjercicio rutinaEjercicio) {
        rutinaEjercicioRepository.save(rutinaEjercicio);
    }

    public void borrarEjercicioRutina(Long id) {
        rutinaEjercicioRepository.deleteById(id);
    }

    public Rutina editar(Long id, Rutina rutinaEntrante) {
        Rutina rutinaExistente = rutinaRepository.findById(id).orElse(null);
        if (rutinaExistente == null) {
            return null;
        }


        rutinaExistente.setNombre(rutinaEntrante.getNombre());
        rutinaExistente.setDescripcion(rutinaEntrante.getDescripcion());
        rutinaExistente.setNivel(rutinaEntrante.getNivel());

        rutinaExistente.getRutinaEjercicios().clear();

        if (rutinaEntrante.getRutinaEjercicios() != null) {
            rutinaEntrante.getRutinaEjercicios().forEach(ejercicio -> {
                ejercicio.setRutina(rutinaExistente);
                rutinaExistente.getRutinaEjercicios().add(ejercicio);
            });
        }

        return rutinaRepository.save(rutinaExistente);
    }
}