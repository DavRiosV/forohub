package com.foro.forohub.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String curso;
}
