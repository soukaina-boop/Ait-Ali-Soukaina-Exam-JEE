package org.sid.controle_jee.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}