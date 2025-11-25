package org.ut4.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ut4.gymmanager.model.GrupoMuscular;
@Repository
public interface GrupoMuscularRepository extends JpaRepository<GrupoMuscular, Long> {
}