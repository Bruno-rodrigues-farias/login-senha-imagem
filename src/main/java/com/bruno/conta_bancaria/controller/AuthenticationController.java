package com.bruno.conta_bancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.conta_bancaria.dtos.UsuarioLoginRequest;
import com.bruno.conta_bancaria.entity.Usuario;
import com.bruno.conta_bancaria.service.TokenService;

@RestController
@RequestMapping("/usuarios")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginRequest data) {
        try {
            // Criação do token de autenticação com as credenciais fornecidas
            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(data.email(), data.password());

            // Autenticação do usuário
            var authentication = this.authenticationManager.authenticate(authToken);

            // Recuperando o usuário autenticado
            Usuario usuario = (Usuario) authentication.getPrincipal();

            // Gerando o token JWT
            String token = tokenService.generatedToken(usuario);

            // Retornando o token gerado com status 200 (OK)
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            // Caso a autenticação falhe, retornando status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
