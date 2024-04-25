package br.com.luishbarros.gestao_vagas.modules.company.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.modules.company.entities.JobsEntity;
import br.com.luishbarros.gestao_vagas.modules.company.useCases.CreateJobEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {
    private static final JobsEntity CreateJobDTO = null;
    @Autowired
    private CreateJobEntity createJobEntity;

    @PostMapping("/")
    public Object create(@Valid @RequestBody JobsEntity jobsEntity, HttpServletRequest request) {
        try {
            var result = createJobEntity.execute(jobsEntity);
            var company_id = request.getAttribute("company_id");
            jobsEntity.setCompanyId(UUID.fromString(company_id.toString()));
            var jobDTO = JobsEntity.builder()
                    .benefits(CreateJobDTO.getBenefits())
                    .companyId(jobsEntity.getCompanyId())
                    .description(CreateJobDTO.getDescription())
                    .level(CreateJobDTO.getLevel());
            return ResponseEntity.status(201).body(jobDTO);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
