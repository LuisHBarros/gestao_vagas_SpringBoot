package br.com.luishbarros.gestao_vagas.modules.candidate.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {
    private String username;
    private String email;
    private String name;
    private String description;
}
