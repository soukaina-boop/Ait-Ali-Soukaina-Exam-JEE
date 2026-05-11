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
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        Agence agence = agenceRepository.findById(vehiculeDTO.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence non trouvée avec id: " + vehiculeDTO.getAgenceId()));

        Vehicule vehicule;

        // Vérifier le type de véhicule par le champ typeVehicule
        if ("VOITURE".equals(vehiculeDTO.getTypeVehicule())) {
            VoitureDTO voitureDTO = (VoitureDTO) vehiculeDTO;
            Voiture voiture = new Voiture();
            voiture.setMarque(voitureDTO.getMarque());
            voiture.setModele(voitureDTO.getModele());
            voiture.setMatricule(voitureDTO.getMatricule());
            voiture.setPrixParJour(voitureDTO.getPrixParJour());
            voiture.setDateMiseEnService(voitureDTO.getDateMiseEnService());
            voiture.setStatut(voitureDTO.getStatut());
            voiture.setNombrePortes(voitureDTO.getNombrePortes());
            voiture.setTypeCarburant(voitureDTO.getTypeCarburant());
            voiture.setBoiteVitesse(voitureDTO.getBoiteVitesse());
            voiture.setAgence(agence);
            vehicule = voiture;

        } else if ("MOTO".equals(vehiculeDTO.getTypeVehicule())) {
            MotoDTO motoDTO = (MotoDTO) vehiculeDTO;
            Moto moto = new Moto();
            moto.setMarque(motoDTO.getMarque());
            moto.setModele(motoDTO.getModele());
            moto.setMatricule(motoDTO.getMatricule());
            moto.setPrixParJour(motoDTO.getPrixParJour());
            moto.setDateMiseEnService(motoDTO.getDateMiseEnService());
            moto.setStatut(motoDTO.getStatut());
            moto.setCylindree(motoDTO.getCylindree());
            moto.setTypeMoto(motoDTO.getTypeMoto());
            moto.setCasqueInclus(motoDTO.getCasqueInclus());
            moto.setAgence(agence);
            vehicule = moto;

        } else {
            throw new RuntimeException("Type de véhicule non supporté: " + vehiculeDTO.getTypeVehicule());
        }

        Vehicule saved = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(saved);
    }

    @Override
    public VehiculeDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + id));
        return vehiculeMapper.toDto(vehicule);
    }

    @Override
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeRepository.findAll().stream()
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesByStatut(StatutVehicule statut) {
        return vehiculeRepository.findByStatut(statut).stream()
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesByAgence(Long agenceId) {
        return vehiculeRepository.findByAgenceId(agenceId).stream()
                .map(vehiculeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + id));

        // Mettre à jour les champs communs
        vehicule.setMarque(vehiculeDTO.getMarque());
        vehicule.setModele(vehiculeDTO.getModele());
        vehicule.setMatricule(vehiculeDTO.getMatricule());
        vehicule.setPrixParJour(vehiculeDTO.getPrixParJour());
        vehicule.setDateMiseEnService(vehiculeDTO.getDateMiseEnService());
        vehicule.setStatut(vehiculeDTO.getStatut());

        if (vehiculeDTO.getAgenceId() != null) {
            Agence agence = agenceRepository.findById(vehiculeDTO.getAgenceId())
                    .orElseThrow(() -> new RuntimeException("Agence non trouvée avec id: " + vehiculeDTO.getAgenceId()));
            vehicule.setAgence(agence);
        }

        Vehicule updated = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(updated);
    }

    @Override
    public void deleteVehicule(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + id));
        vehiculeRepository.delete(vehicule);
    }

    @Override
    public VehiculeDTO updateStatut(Long id, StatutVehicule statut) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + id));
        vehicule.setStatut(statut);
        Vehicule updated = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(updated);
    }
}