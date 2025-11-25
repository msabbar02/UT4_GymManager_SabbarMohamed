package org.ut4.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ut4.gymmanager.model.Ejercicio;

import java.util.List;
@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByGrupoMuscular_NombreIgnoreCase(String nombre);
}