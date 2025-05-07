package com.bruno.conta_bancaria.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bruno.conta_bancaria.entity.Usuario;

@Service
public class TokenService {

    
    Algorithm algorithm = Algorithm.HMAC256("minhaChave");

    public String generatedToken( Usuario usuario){

        return JWT.create()
        .withIssuer("conta.bancaria")
        .withSubject(usuario.getId().toString())
        .withClaim("Email", usuario.getEmail())
        .withClaim("Nome", usuario.getNome())
        .withClaim("Role", usuario.getRole().name())
        .withClaim("Foto", usuario.getFotoPath())
        .withExpiresAt(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
        .sign(algorithm);
    }

    public String validateToken(String token){
        try{

           return JWT.require(algorithm)
            .withIssuer("conta.bancaria")
            .build()
            .verify(token)
            .getSubject();
        }catch(JWTVerificationException exception){
            return null;
        }
    }


}
