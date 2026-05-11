package org.sid.controle_jee.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sid.controle_jee.enums.TypeMoto;
import org.sid.controle_jee.enums.OuiNon;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotoDTO extends VehiculeDTO {
    private Double cylindree;
    private TypeMoto typeMoto;
    private OuiNon casqueInclus;
}