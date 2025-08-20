package com.foro.forohub.service;

import com.foro.forohub.dto.TopicoDTO;
import com.foro.forohub.dto.RespuestaTopicoDTO;
import com.foro.forohub.model.Topico;
import com.foro.forohub.repository.TopicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TopicoService {
    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Topico crearTopico(TopicoDTO dto) {
        if (repository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            throw new RuntimeException("El tópico ya existe");
        }
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(dto.autor());
        topico.setCurso(dto.curso());
        topico.setStatus("ABIERTO");
        return repository.save(topico);
    }

    public List<Topico> listarTopicos() {
        return repository.findAll();
    }

    public Topico buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }

    @Transactional
    public Topico actualizarTopico(Long id, TopicoDTO dto) {
        Topico topico = buscarPorId(id);
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setAutor(dto.autor());
        topico.setCurso(dto.curso());
        return repository.save(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado");
        }
        repository.deleteById(id);
    }
}
