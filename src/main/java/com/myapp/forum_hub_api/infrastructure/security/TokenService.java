package com.myapp.forum_hub_api.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.myapp.forum_hub_api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Forum_hub_api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algoritmo);
        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token.", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Forum_hub_api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }
        catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT expirado ou inválido.");
        }
    }

    private Instant gerarDataDeExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
