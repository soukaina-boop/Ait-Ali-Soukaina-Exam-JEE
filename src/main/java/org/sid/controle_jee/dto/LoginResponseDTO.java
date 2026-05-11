package org.sid.controle_jee.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String username;
    private String role;
}