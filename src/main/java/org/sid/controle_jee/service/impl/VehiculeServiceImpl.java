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

        if (vehiculeDTO instanceof VoitureDTO) {
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

        } else if (vehiculeDTO instanceof MotoDTO) {
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
            throw new RuntimeException("Type de véhicule non supporté");
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

        // Mettre à jour les attributs spécifiques selon le type
        if (vehicule instanceof Voiture && vehiculeDTO instanceof VoitureDTO) {
            VoitureDTO vd = (VoitureDTO) vehiculeDTO;
            ((Voiture) vehicule).setNombrePortes(vd.getNombrePortes());
            ((Voiture) vehicule).setTypeCarburant(vd.getTypeCarburant());
            ((Voiture) vehicule).setBoiteVitesse(vd.getBoiteVitesse());
        } else if (vehicule instanceof Moto && vehiculeDTO instanceof MotoDTO) {
            MotoDTO md = (MotoDTO) vehiculeDTO;
            ((Moto) vehicule).setCylindree(md.getCylindree());
            ((Moto) vehicule).setTypeMoto(md.getTypeMoto());
            ((Moto) vehicule).setCasqueInclus(md.getCasqueInclus());
        }

        Vehicule updated = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(updated);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
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