package br.com.luishbarros.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {

    @Value("${jwt.secret}")
    private String secret;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm alg = Algorithm.HMAC256(secret.getBytes());
        try {
            JWT.require(alg).build().verify(token).getSubject();
            return token;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }

    }
}
