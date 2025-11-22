package org.ut4.gymmanager.repository;

import org.springframework.stereotype.Repository;
import org.ut4.gymmanager.model.GrupoMuscular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Repository
public class GrupoMuscularRepositoryFake {

    private final List<GrupoMuscular> grupos = new ArrayList<>();

    /**
     * Constructor que inicializa la lista con algunos datos de ejemplo.
     */
    public GrupoMuscularRepositoryFake() {
        grupos.add(new GrupoMuscular(1L, "Pectoral", "Grupo muscular del pecho."));
        grupos.add(new GrupoMuscular(2L, "Espalda", "Zona dorsal y lumbar."));
        grupos.add(new GrupoMuscular(3L, "Pierna", "Cuádriceps, isquios y glúteos."));
        grupos.add(new GrupoMuscular(4L, "Hombro", "Deltoides y zona del hombro."));
        grupos.add(new GrupoMuscular(5L, "Bíceps", "Parte frontal del brazo."));
    }

    /*
     * metodo findALL()
     * return list de grupoMuscular
     */
    public List<GrupoMuscular> findAll() {
        return grupos;
    }

    /*
     * metodo findById()
     * @param id
     * @return GrupoMuscular
     */

    public Optional<GrupoMuscular> findById(Long id) {
        return grupos.stream().filter(g -> g.getId().equals(id)).findFirst();
    }
}
