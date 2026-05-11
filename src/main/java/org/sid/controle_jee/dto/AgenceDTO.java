package org.sid.controle_jee.dto;

import lombok.Data;
import java.util.List;

@Data
public class AgenceDTO {
    private Long id;
    private String nom;
    private String adresse;
    private String ville;
    private String telephone;
    private List<VehiculeDTO> vehicules;
}