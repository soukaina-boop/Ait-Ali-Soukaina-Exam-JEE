package org.sid.controle_jee.dto;

import lombok.Data;
import org.sid.controle_jee.enums.StatutVehicule;
import java.time.LocalDate;

@Data
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String matricule;
    private Double prixParJour;
    private LocalDate dateMiseEnService;
    private StatutVehicule statut;
    private Long agenceId;
    private String typeVehicule; // "VOITURE" ou "MOTO"
}