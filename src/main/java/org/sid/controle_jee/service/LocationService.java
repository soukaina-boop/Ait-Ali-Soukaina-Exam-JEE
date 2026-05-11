package org.sid.controle_jee.service;

import org.sid.controle_jee.dto.LocationDTO;
import java.util.List;

public interface LocationService {
    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocationById(Long id);
    List<LocationDTO> getAllLocations();
    List<LocationDTO> getLocationsByVehicule(Long vehiculeId);
    void deleteLocation(Long id);
}