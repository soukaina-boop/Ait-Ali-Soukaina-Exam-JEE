package org.sid.controle_jee.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.sid.controle_jee.enums.Role;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}