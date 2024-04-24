package br.com.luishbarros.gestao_vagas.modules.company.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luishbarros.gestao_vagas.modules.company.DTO.AuthCompanyDTO;
import br.com.luishbarros.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth/company")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    public Object execute(@RequestBody AuthCompanyDTO data) {
        try {
            var response = this.authCompanyUseCase.execute(data);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
