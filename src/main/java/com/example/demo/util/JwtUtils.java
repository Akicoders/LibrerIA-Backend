package com.example.demo.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Slf4j
@Component
public class JwtUtils  {
    @Value("${security.jwt.key.private}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    private DecodedJWT decodedJWT;

    public String createToken(Authentication auth) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            String username = auth.getPrincipal().toString();
            String authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

            String token = JWT.create()
                    .withIssuer(this.userGenerator)
                    .withSubject(username)
                    .withClaim("authorities", authorities)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                    .withJWTId(UUID.randomUUID().toString())
                    .withNotBefore(new Date(System.currentTimeMillis()))
                    .sign(algorithm);
            log.info("Se creo un token de expiracion de 1 hora a el usuario: " + username);
            return token;
        }catch (JWTVerificationException e) {
            log.warn(String.valueOf(e));
            throw new JWTVerificationException("Token creation failed");
        }
    }

    public DecodedJWT verifyToken(String token) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
           JWTVerifier verifier = JWT.require(algorithm)
                   .withIssuer(userGenerator)
                   .build();
           decodedJWT = verifier.verify(token);
           log.info("Se decodifico el jwt correctamente");
           return decodedJWT;
       }catch (JWTVerificationException e) {
           log.warn(String.valueOf(e));
           throw new JWTVerificationException("Token invalid, not Authorized");
       }

    }

    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String,Claim> returnAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }





}
