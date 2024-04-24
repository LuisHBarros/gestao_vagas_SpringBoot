package br.com.luishbarros.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luishbarros.gestao_vagas.exception.UserFoundException;
import br.com.luishbarros.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.luishbarros.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var encodePassword = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(encodePassword);
        return this.companyRepository.save(companyEntity);
    }
}