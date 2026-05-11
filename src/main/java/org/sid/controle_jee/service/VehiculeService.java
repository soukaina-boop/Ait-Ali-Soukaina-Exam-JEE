package org.sid.controle_jee.service;

import org.sid.controle_jee.dto.VehiculeDTO;
import org.sid.controle_jee.enums.StatutVehicule;
import java.util.List;

public interface VehiculeService {
    VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO);
    VehiculeDTO getVehiculeById(Long id);
    List<VehiculeDTO> getAllVehicules();
    List<VehiculeDTO> getVehiculesByStatut(StatutVehicule statut);
    List<VehiculeDTO> getVehiculesByAgence(Long agenceId);
    VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO);
    void deleteVehicule(Long id);
    VehiculeDTO updateStatut(Long id, StatutVehicule statut);
}