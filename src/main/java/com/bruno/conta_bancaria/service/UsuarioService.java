package com.bruno.conta_bancaria.service;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bruno.conta_bancaria.entity.Usuario;
import com.bruno.conta_bancaria.entity.UsuarioRole;
import com.bruno.conta_bancaria.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    @Value("${upload.dir}")
    private String uploadDir;

    public String salvarImagem(MultipartFile imagem) throws IOException{
        File pastaUpload = new File(uploadDir);

        if(!pastaUpload.exists()){
            pastaUpload.mkdirs();
        }

        String nomeArquivo = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();
        Path caminhoArquivo = Path.of(uploadDir, nomeArquivo);

        Files.copy(imagem.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        return nomeArquivo;
    }

   public Usuario criarConta(String nome,String email, String senhaCriptografada, UsuarioRole role, MultipartFile foto) throws IOException {
    String nomeArquivoFoto = salvarImagem(foto);

    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setPassword(senhaCriptografada);
    usuario.setEmail(email);
    usuario.setRole(role);
   
    usuario.setFotoPath(nomeArquivoFoto);

    return usuarioRepository.save(usuario);
}
    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();

      
    }


}
