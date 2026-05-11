package org.sid.controle_jee.service;

import org.sid.controle_jee.dto.AgenceDTO;
import java.util.List;

public interface AgenceService {
    AgenceDTO createAgence(AgenceDTO agenceDTO);
    AgenceDTO getAgenceById(Long id);
    List<AgenceDTO> getAllAgences();
    AgenceDTO updateAgence(Long id, AgenceDTO agenceDTO);
    void deleteAgence(Long id);
}