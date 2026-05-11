package org.sid.controle_jee.mapper;

import org.sid.controle_jee.dto.LocationDTO;
import org.sid.controle_jee.entities.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationDTO toDto(Location location) {
        if (location == null) return null;

        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setDateDebut(location.getDateDebut());
        dto.setDateFin(location.getDateFin());
        dto.setMontantTotal(location.getMontantTotal());

        if (location.getVehicule() != null) {
            dto.setVehiculeId(location.getVehicule().getId());
            dto.setVehiculeMatricule(location.getVehicule().getMatricule());
        }

        return dto;
    }

    public Location toEntity(LocationDTO dto) {
        if (dto == null) return null;

        Location location = new Location();
        location.setId(dto.getId());
        location.setDateDebut(dto.getDateDebut());
        location.setDateFin(dto.getDateFin());
        location.setMontantTotal(dto.getMontantTotal());

        return location;
    }
}