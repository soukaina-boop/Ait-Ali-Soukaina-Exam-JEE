package org.sid.controle_jee.mappers;

import org.sid.controle_jee.dto.VehiculeDTO;
import org.sid.controle_jee.dtos.VoitureDTO;
import org.sid.controle_jee.dto.MotoDTO;
import org.sid.controle_jee.entities.Vehicule;
import org.sid.controle_jee.entities.Voiture;
import org.sid.controle_jee.entities.Moto;
import org.springframework.stereotype.Component;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDto(Vehicule vehicule) {
        if (vehicule == null) return null;

        VehiculeDTO dto = new VehiculeDTO();
        dto.setId(vehicule.getId());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setMatricule(vehicule.getMatricule());
        dto.setPrixParJour(vehicule.getPrixParJour());
        dto.setDateMiseEnService(vehicule.getDateMiseEnService());
        dto.setStatut(vehicule.getStatut());

        if (vehicule.getAgence() != null) {
            dto.setAgenceId(vehicule.getAgence().getId());
        }

        // Déterminer le type de véhicule
        if (vehicule instanceof Voiture) {
            dto.setTypeVehicule("VOITURE");
        } else if (vehicule instanceof Moto) {
            dto.setTypeVehicule("MOTO");
        }

        return dto;
    }

    public VoitureDTO toVoitureDto(Voiture voiture) {
        if (voiture == null) return null;

        VoitureDTO dto = new VoitureDTO();
        dto.setId(voiture.getId());
        dto.setMarque(voiture.getMarque());
        dto.setModele(voiture.getModele());
        dto.setMatricule(voiture.getMatricule());
        dto.setPrixParJour(voiture.getPrixParJour());
        dto.setDateMiseEnService(voiture.getDateMiseEnService());
        dto.setStatut(voiture.getStatut());
        dto.setNombrePortes(voiture.getNombrePortes());
        dto.setTypeCarburant(voiture.getTypeCarburant());
        dto.setBoiteVitesse(voiture.getBoiteVitesse());

        if (voiture.getAgence() != null) {
            dto.setAgenceId(voiture.getAgence().getId());
        }
        dto.setTypeVehicule("VOITURE");

        return dto;
    }

    public MotoDTO toMotoDto(Moto moto) {
        if (moto == null) return null;

        MotoDTO dto = new MotoDTO();
        dto.setId(moto.getId());
        dto.setMarque(moto.getMarque());
        dto.setModele(moto.getModele());
        dto.setMatricule(moto.getMatricule());
        dto.setPrixParJour(moto.getPrixParJour());
        dto.setDateMiseEnService(moto.getDateMiseEnService());
        dto.setStatut(moto.getStatut());
        dto.setCylindree(moto.getCylindree());
        dto.setTypeMoto(moto.getTypeMoto());
        dto.setCasqueInclus(moto.getCasqueInclus());

        if (moto.getAgence() != null) {
            dto.setAgenceId(moto.getAgence().getId());
        }
        dto.setTypeVehicule("MOTO");

        return dto;
    }

    public Vehicule toEntity(VehiculeDTO dto) {
        if (dto == null) return null;

        // Pour les conversions simples, on retourne un véhicule de base
        // L'implémentation complète se fera dans le service
        return null;
    }
}