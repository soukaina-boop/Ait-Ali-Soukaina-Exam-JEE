package org.sid.controle_jee.services.impl;

import lombok.RequiredArgsConstructor;
import org.sid.controle_jee.dto.LocationDTO;
import org.sid.controle_jee.entities.Location;
import org.sid.controle_jee.entities.Vehicule;
import org.sid.controle_jee.enums.StatutVehicule;
import org.sid.controle_jee.mapper.LocationMapper;
import org.sid.controle_jee.repository.LocationRepository;
import org.sid.controle_jee.repository.VehiculeRepository;
import org.sid.controle_jee.service.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LocationMapper locationMapper;

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Vehicule vehicule = vehiculeRepository.findById(locationDTO.getVehiculeId())
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + locationDTO.getVehiculeId()));

        // Vérifier si le véhicule est disponible
        if (vehicule.getStatut() != StatutVehicule.DISPONIBLE) {
            throw new RuntimeException("Le véhicule avec matricule " + vehicule.getMatricule() + " n'est pas disponible pour location");
        }

        // Calculer le montant total
        long jours = ChronoUnit.DAYS.between(locationDTO.getDateDebut(), locationDTO.getDateFin());
        if (jours <= 0) {
            throw new RuntimeException("La date de fin doit être après la date de début");
        }
        double montantTotal = jours * vehicule.getPrixParJour();
        locationDTO.setMontantTotal(montantTotal);

        Location location = locationMapper.toEntity(locationDTO);
        location.setVehicule(vehicule);

        // Changer le statut du véhicule à "LOUE"
        vehicule.setStatut(StatutVehicule.LOUE);
        vehiculeRepository.save(vehicule);

        Location saved = locationRepository.save(location);
        return locationMapper.toDto(saved);
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée avec id: " + id));
        return locationMapper.toDto(location);
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDTO> getLocationsByVehicule(Long vehiculeId) {
        return locationRepository.findByVehiculeId(vehiculeId).stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée avec id: " + id));

        // Rendre le véhicule disponible à nouveau
        Vehicule vehicule = location.getVehicule();
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        vehiculeRepository.save(vehicule);

        locationRepository.deleteById(id);
    }
}