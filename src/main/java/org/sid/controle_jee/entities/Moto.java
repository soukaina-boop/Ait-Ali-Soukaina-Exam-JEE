package org.sid.controle_jee.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sid.controle_jee.enums.OuiNon;
import org.sid.controle_jee.enums.TypeMoto;

@Entity
@DiscriminatorValue("MOTO")
@Data
@EqualsAndHashCode(callSuper = true)
public class Moto extends Vehicule {
    private Double cylindree;

    @Enumerated(EnumType.STRING)
    private TypeMoto typeMoto;

    @Enumerated(EnumType.STRING)
    private OuiNon casqueInclus;
}