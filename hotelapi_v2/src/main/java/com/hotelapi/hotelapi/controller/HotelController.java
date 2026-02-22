package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.mapper.HotelMapper;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/hoteis")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService service;
    private final HotelMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> salvar(@RequestBody @Valid HotelDTO dto) {
        Hotel entidade = mapper.toEntity(dto);

        service.salvar(entidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<HotelDTO> consultaPorId(@PathVariable("id") Long id) {
        Hotel entidade = service.consultaPorId(id);
        HotelDTO dto = mapper.toDTO(entidade);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<HotelDTO>> pesquisaFiltrada(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "num-pagina", defaultValue = "0") Integer numPagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "3") Integer tamanhoPagina
    ) {
        Page<Hotel> resultado = service.pesquisaFiltrada(nome, estado, numPagina, tamanhoPagina);
        Page<HotelDTO> resultadoDTO = resultado.map(mapper::toDTO);
        return ResponseEntity.ok(resultadoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody @Valid HotelDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
