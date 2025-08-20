package com.foro.forohub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMs;

    public String generarToken(String username) {
        Algorithm alg = Algorithm.HMAC256(secret);
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + expirationMs);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(ahora)
                .withExpiresAt(expira)
                .withIssuer("forohub")
                .sign(alg);
    }

    public String validarYObtenerUsuario(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        DecodedJWT decoded = JWT.require(alg)
                .withIssuer("forohub")
                .build()
                .verify(token);
        return decoded.getSubject();
    }
}
