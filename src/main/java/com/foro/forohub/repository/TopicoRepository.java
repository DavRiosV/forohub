package com.foro.forohub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findByCursoContainingIgnoreCaseAndFechaCreacionBetween(
            String curso, java.time.LocalDateTime desde, java.time.LocalDateTime hasta, Pageable pageable);
}
