package com.microrent.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.token.secret}")
    private String secretKey;
    @Value("${jwt.token.expired}")
    private Long ValidityInHours;

    public String generateToken(String email){
        Date expirationDate = Date.from(ZonedDateTime.now().plusHours(ValidityInHours).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withIssuer("MicroRentApp")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public boolean validateToken(String token){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey))
                    .withSubject("User details")
                    .withIssuer("MicroRentApp")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    public String retrieveClaim(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withSubject("User details")
                .withIssuer("MicroRentApp")
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim("email").asString();
    }
}
