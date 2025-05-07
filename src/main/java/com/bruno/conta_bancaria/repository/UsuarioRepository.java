package com.bruno.conta_bancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.conta_bancaria.entity.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Usuario findByEmail(String email);
} 
