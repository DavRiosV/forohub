package com.foro.forohub.controller;

import com.foro.forohub.dto.TopicoDTO;
import com.foro.forohub.dto.RespuestaTopicoDTO;
import com.foro.forohub.model.Topico;
import com.foro.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    public Topico crear(@RequestBody @Valid TopicoDTO dto) {
        return service.crearTopico(dto);
    }

    @GetMapping
    public List<Topico> listar() {
        return service.listarTopicos();
    }

    @GetMapping("/{id}")
    public Topico detalle(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Topico actualizar(@PathVariable Long id, @RequestBody @Valid TopicoDTO dto) {
        return service.actualizarTopico(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarTopico(id);
    }

    @GetMapping("/topicos/pagina")
    public Page<Topico> listarPageable(
            @RequestParam(defaultValue = "") String curso,
            @RequestParam(required = false) String anio,
            @org.springframework.data.web.PageableDefault(size = 10, sort = "fechaCreacion")
            org.springframework.data.domain.Pageable pageable
    ) {
        var desde = (anio != null) ? java.time.LocalDateTime.of(Integer.parseInt(anio),1,1,0,0) : java.time.LocalDateTime.MIN;
        var hasta = (anio != null) ? java.time.LocalDateTime.of(Integer.parseInt(anio),12,31,23,59) : java.time.LocalDateTime.MAX;
        return repository.findByCursoContainingIgnoreCaseAndFechaCreacionBetween(curso, desde, hasta, pageable);
    }

}
