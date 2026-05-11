package org.sid.controle_jee.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sid.controle_jee.enums.BoiteVitesse;
import org.sid.controle_jee.enums.TypeCarburant;

@Entity
@DiscriminatorValue("VOITURE")
@Data
@EqualsAndHashCode(callSuper = true)
public class Voiture extends Vehicule {
    private Integer nombrePortes;

    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;

    @Enumerated(EnumType.STRING)
    private BoiteVitesse boiteVitesse;
}