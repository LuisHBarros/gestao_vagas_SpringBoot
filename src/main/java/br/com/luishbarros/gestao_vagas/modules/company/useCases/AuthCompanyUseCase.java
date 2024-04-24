package br.com.luishbarros.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.luishbarros.gestao_vagas.modules.company.DTO.AuthCompanyDTO;
import br.com.luishbarros.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public String execute(AuthCompanyDTO data) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(data.getUsername()).orElseThrow(
                () -> new AuthenticationException("Company not found"));
        if (!passwordEncoder.matches(data.getPassword(), company.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        Algorithm alg = Algorithm.HMAC256(secret.getBytes());
        return JWT.create().withIssuer("gestao-vagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(alg);

    }
}
