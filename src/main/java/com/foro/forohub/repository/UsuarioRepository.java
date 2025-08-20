package com.foro.forohub.repository;

import com.foro.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
}
