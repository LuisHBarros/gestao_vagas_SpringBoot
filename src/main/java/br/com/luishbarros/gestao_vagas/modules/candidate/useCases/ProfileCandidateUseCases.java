package br.com.luishbarros.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.luishbarros.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.luishbarros.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.luishbarros.gestao_vagas.modules.candidate.DTO.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCases {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID id) {
        var response = this.candidateRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));

        return ProfileCandidateResponseDTO.builder()
                .description(response.getDescription())
                .email(response.getEmail())
                .name(response.getName())
                .username(response.getUsername())
                .build();

    }
}
