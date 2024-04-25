package br.com.luishbarros.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.modules.candidate.DTO.AuthCandidateRequestDTO;
import br.com.luishbarros.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCases;

@RestController
@RequestMapping("/candidate/auth")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCases authCandidateUseCases;

    @PostMapping("/")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var response = authCandidateUseCases.execute(authCandidateRequestDTO);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
