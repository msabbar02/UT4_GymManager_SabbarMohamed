package org.ut4.gymmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ut4.gymmanager.model.Rutina;
@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {

}
