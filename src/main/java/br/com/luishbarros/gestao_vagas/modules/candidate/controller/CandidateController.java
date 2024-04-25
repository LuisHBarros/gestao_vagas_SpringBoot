package br.com.luishbarros.gestao_vagas.modules.candidate.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.exception.UserFoundException;
import br.com.luishbarros.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.luishbarros.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.luishbarros.gestao_vagas.modules.candidate.DTO.ProfileCandidateResponseDTO;
import br.com.luishbarros.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.luishbarros.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    ProfileCandidateUseCases profileCandidateUseCases;

    @GetMapping("")
    public String helloWorld() {
        return "Hello, world";
    }

    @PostMapping("/")
    public Object create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Object profile(@PathVariable UUID id) {
        try {
            var result = profileCandidateUseCases.execute(id);
            return ResponseEntity.status(200).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
