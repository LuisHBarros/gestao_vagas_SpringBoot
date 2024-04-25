package br.com.luishbarros.gestao_vagas.modules.candidate.useCases;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.luishbarros.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.luishbarros.gestao_vagas.modules.candidate.DTO.AuthCandidateRequestDTO;
import br.com.luishbarros.gestao_vagas.modules.candidate.DTO.AuthCandidateResponseDTO;
import ch.qos.logback.core.util.Duration;

@Service
public class AuthCandidateUseCases {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO data) {
        var candidate = this.candidateRepository.findByUsername(data.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username invalid");
                });
        if (!this.passwordEncoder.matches(data.password(), candidate.getPassword())) {
            throw new UsernameNotFoundException("Password invalid");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expires_in = Instant.now().plus(java.time.Duration.ofHours(2));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expires_in)
                .sign(algorithm);
        return AuthCandidateResponseDTO.builder().access_token(token).expires_in(expires_in.toEpochMilli()).build();
    }
}
