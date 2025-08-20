package com.foro.forohub.dto;

public record TopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso
) {
}
