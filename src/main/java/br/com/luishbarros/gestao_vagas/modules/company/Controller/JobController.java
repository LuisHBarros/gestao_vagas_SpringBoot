package br.com.luishbarros.gestao_vagas.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.modules.company.entities.JobsEntity;
import br.com.luishbarros.gestao_vagas.modules.company.useCases.CreateJobEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobEntity createJobEntity;

    @PostMapping("/")
    public Object create(@Valid @RequestBody JobsEntity jobsEntity) {
        try {
            var result = createJobEntity.execute(jobsEntity);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
