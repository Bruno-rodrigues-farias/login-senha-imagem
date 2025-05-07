package com.bruno.conta_bancaria.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.conta_bancaria.entity.Usuario;
import com.bruno.conta_bancaria.entity.UsuarioRole;
import com.bruno.conta_bancaria.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${upload.dir}")
    private String uploadDir;


    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarConta(
       @RequestParam("email") String email,
       @RequestParam("password") String password,
       @RequestParam("nome") String nome,
       @RequestParam("role") UsuarioRole role,
       @RequestParam("foto") MultipartFile foto) {
        try {
            String senhaCriptografada = passwordEncoder.encode(password);
            Usuario novoUsuario = usuarioService.criarConta(nome, email, senhaCriptografada, role, foto);
            return ResponseEntity.ok(novoUsuario);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.buscarUsuarios();
    }

   
    @GetMapping("/foto")
    public ResponseEntity<byte[]> exibirFoto(@RequestParam("nomeArquivo") String nomeArquivo) {
      try {
         Path caminhoFoto = Path.of(uploadDir, nomeArquivo);
         if (!Files.exists(caminhoFoto)) {
               return ResponseEntity.notFound().build();
            }
            byte[] imagemBytes = Files.readAllBytes(caminhoFoto);
            String contentType = Files.probeContentType(caminhoFoto);

            return ResponseEntity.ok()
                  .header("Content-Type", contentType)
                  .body(imagemBytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
