package org.sid.controle_jee.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LocationDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double montantTotal;
    private Long vehiculeId;
    private String vehiculeMatricule;
}