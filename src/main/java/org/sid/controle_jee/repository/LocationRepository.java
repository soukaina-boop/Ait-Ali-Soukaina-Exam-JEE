package org.sid.controle_jee.repository;

import org.sid.controle_jee.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByVehiculeId(Long vehiculeId);
}