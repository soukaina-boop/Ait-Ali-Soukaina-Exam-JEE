package org.sid.controle_jee.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sid.controle_jee.dto.VehiculeDTO;
import org.sid.controle_jee.enums.TypeCarburant;
import org.sid.controle_jee.enums.BoiteVitesse;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoitureDTO extends VehiculeDTO {
    private Integer nombrePortes;
    private TypeCarburant typeCarburant;
    private BoiteVitesse boiteVitesse;
}