package org.sid.controle_jee.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.controle_jee.dto.*;
import org.sid.controle_jee.entities.*;
import org.sid.controle_jee.enums.StatutVehicule;
import org.sid.controle_jee.mappers.VehiculeMapper;
import org.sid.controle_jee.repository.AgenceRepository;
import org.sid.controle_jee.repository.VehiculeRepository;
import org.sid.controle_jee.service.VehiculeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final VehiculeMapper mapper;

    @Override
    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        Agence agence = agenceRepository.findById(vehiculeDTO.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        Vehicule vehicule;
        if (vehiculeDTO instanceof VoitureDTO) {
            vehicule = new Voiture();
            VoitureDTO vd = (VoitureDTO) vehiculeDTO;
            ((Voiture) vehicule).setNombrePortes(vd.getNombrePortes());
            ((Voiture) vehicule).setTypeCarburant(vd.getTypeCarburant());
            ((Voiture) vehicule).setBoiteVitesse(vd.getBoiteVitesse());
        } else if (vehiculeDTO instanceof MotoDTO) {
            vehicule = new Moto();
            MotoDTO md = (MotoDTO) vehiculeDTO;
            ((Moto) vehicule).setCylindree(md.getCylindree());
            ((Moto) vehicule).setTypeMoto(md.getTypeMoto());
            ((Moto) vehicule).setCasqueInclus(md.getCasqueInclus());
        } else {
            throw new RuntimeException("Type de véhicule non supporté");
        }

        vehicule.setMarque(vehiculeDTO.getMarque());
        vehicule.setModele(vehiculeDTO.getModele());
        vehicule.setMatricule(vehiculeDTO.getMatricule());
        vehicule.setPrixParJour(vehiculeDTO.getPrixParJour());
        vehicule.setDateMiseEnService(vehiculeDTO.getDateMiseEnService());
        vehicule.setStatut(vehiculeDTO.getStatut());
        vehicule.setAgence(agence);

        Vehicule saved = vehiculeRepository.save(vehicule);
        return mapper.toDto(saved);
    }

    @Override
    public VehiculeDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        return mapper.toDto(vehicule);
    }

    @Override
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesByStatut(StatutVehicule statut) {
        return vehiculeRepository.findByStatut(statut).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesByAgence(Long agenceId) {
        return vehiculeRepository.findByAgenceId(agenceId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        vehicule.setMarque(vehiculeDTO.getMarque());
        vehicule.setModele(vehiculeDTO.getModele());
        vehicule.setMatricule(vehiculeDTO.getMatricule());
        vehicule.setPrixParJour(vehiculeDTO.getPrixParJour());
        vehicule.setDateMiseEnService(vehiculeDTO.getDateMiseEnService());
        vehicule.setStatut(vehiculeDTO.getStatut());

        if (vehiculeDTO.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(vehiculeDTO.getAgenceId())
                    .orElseThrow(() -> new RuntimeException("Agence non trouvée"));
            vehicule.setAgence(agence);
        }

        return mapper.toDto(vehiculeRepository.save(vehicule));
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public VehiculeDTO updateStatut(Long id, StatutVehicule statut) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        vehicule.setStatut(statut);
        return mapper.toDto(vehiculeRepository.save(vehicule));
    }
}