package com.foro.forohub.config;


import com.foro.forohub.model.Usuario;
import com.foro.forohub.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            String user = "admin";
            if (!repo.existsByUsername(user)) {
                String raw = "123456";
                String hash = encoder.encode(raw);
                repo.save(new Usuario(user, hash, "ADMIN"));
                System.out.println("Usuario de prueba creado -> username: admin / password: 123456");
            }
        };
    }
}
