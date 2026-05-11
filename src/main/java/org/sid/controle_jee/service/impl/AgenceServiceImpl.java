package org.sid.controle_jee.service.impl;

import lombok.RequiredArgsConstructor;
import org.sid.controle_jee.dto.AgenceDTO;
import org.sid.controle_jee.entities.Agence;
import org.sid.controle_jee.mapper.AgenceMapper;
import org.sid.controle_jee.repository.AgenceRepository;
import org.sid.controle_jee.service.AgenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceDTO createAgence(AgenceDTO agenceDTO) {
        Agence agence = agenceMapper.toEntity(agenceDTO);
        Agence saved = agenceRepository.save(agence);
        return agenceMapper.toDto(saved);
    }

    @Override
    public AgenceDTO getAgenceById(Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée avec id: " + id));
        return agenceMapper.toDto(agence);
    }

    @Override
    public List<AgenceDTO> getAllAgences() {
        return agenceRepository.findAll().stream()
                .map(agenceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgenceDTO updateAgence(Long id, AgenceDTO agenceDTO) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée avec id: " + id));

        agence.setNom(agenceDTO.getNom());
        agence.setAdresse(agenceDTO.getAdresse());
        agence.setVille(agenceDTO.getVille());
        agence.setTelephone(agenceDTO.getTelephone());

        Agence updated = agenceRepository.save(agence);
        return agenceMapper.toDto(updated);
    }

    @Override
    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }
}