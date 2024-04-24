package br.com.luishbarros.gestao_vagas.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.luishbarros.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public Object create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var result = createCompanyUseCase.execute(companyEntity);
            return ResponseEntity.status(201).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
