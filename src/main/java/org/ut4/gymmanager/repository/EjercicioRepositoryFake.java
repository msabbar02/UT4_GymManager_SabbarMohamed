package org.ut4.gymmanager.repository;

import org.springframework.stereotype.Repository;
import org.ut4.gymmanager.model.Ejercicio;
import org.ut4.gymmanager.model.GrupoMuscular;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EjercicioRepositoryFake {

    public List<Ejercicio> ejercicios = new ArrayList<>();

    /**
     * Constructor que inicializa algunos ejercicios de ejemplo.
     * <p>
     * Para simplificar, los grupos musculares se crean aquí de forma local.
     * En una implementación real, los ejercicios deberían referenciar
     * entidades persistidas de {@link GrupoMuscular}.
     */
    public EjercicioRepositoryFake() {
        GrupoMuscular pectoral = new GrupoMuscular(1L, "Pectoral", "Grupo muscular del pecho.");
        GrupoMuscular espalda = new GrupoMuscular(2L, "Espalda", "Zona dorsal y lumbar.");
        GrupoMuscular pierna = new GrupoMuscular(3L, "Pierna", "Cuádriceps, isquios y glúteos.");
        GrupoMuscular hombro = new GrupoMuscular(4L, "Hombro", "Deltoides y zona del hombro.");

        ejercicios.add(new Ejercicio(
                1L,
                "Press banca",
                "Ejercicio básico de empuje para pectoral.",
                "INTERMEDIO",
                pectoral));

        ejercicios.add(new Ejercicio(
                2L,
                "Aperturas con mancuernas",
                "Trabajo de apertura para pectoral.",
                "INTERMEDIO",
                pectoral));

        ejercicios.add(new Ejercicio(
                3L,
                "Dominadas",
                "Ejercicio de tracción para espalda.",
                "AVANZADO",
                espalda));

        ejercicios.add(new Ejercicio(
                4L,
                "Remo con barra",
                "Remo horizontal para espalda.",
                "INTERMEDIO",
                espalda));

        ejercicios.add(new Ejercicio(
                5L,
                "Sentadilla trasera",
                "Sentadilla clásica con barra.",
                "AVANZADO",
                pierna));

        ejercicios.add(new Ejercicio(
                6L,
                "Prensa de piernas",
                "Trabajo de piernas en máquina de prensa.",
                "PRINCIPIANTE",
                pierna));

        ejercicios.add(new Ejercicio(
                7L,
                "Press militar",
                "Ejercicio de empuje vertical para hombros.",
                "INTERMEDIO",
                hombro));
    }

    public List<Ejercicio> findAll() {
        return ejercicios;
    }

    public Optional<Ejercicio> findById(Long id) {
        return ejercicios.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<Ejercicio> findByGrupoMuscular(String grupoMuscular) {
        return ejercicios.stream().filter(e -> e.getGrupoMuscular().getNombre().equals(grupoMuscular)).toList();
    }

}
