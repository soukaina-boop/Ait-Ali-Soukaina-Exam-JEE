package org.sid.controle_jee.repository;

import org.sid.controle_jee.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
}