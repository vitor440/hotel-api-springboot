package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.UsuarioDTO;
import com.hotelapi.hotelapi.mapper.UsuarioMapper;
import com.hotelapi.hotelapi.model.Usuario;
import com.hotelapi.hotelapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody UsuarioDTO dto) {
        Usuario usuario = mapper.toEntity(dto);
        service.salvar(usuario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> consultaPorId(@PathVariable("id") Long id) {
        Usuario usuario = service.consultaPorId(id);
        UsuarioDTO dto = mapper.toDTO(usuario);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        List<Usuario> resultado = service.listarTodos();
        List<UsuarioDTO> resultadoDTO = resultado.stream().map(mapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(resultadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto) {
        Usuario aux = mapper.toEntity(dto);
        service.atualizar(id, aux);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
        service.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}
