package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.mapper.QuartoMapper;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.service.QuartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final QuartoService service;
    private final QuartoMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroQuartoDTO dto) {
        Quarto entidade = mapper.toEntity(dto);
        service.salvar(entidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaQuartoDTO> consultaPorId(@PathVariable("id") Long id) {
        Quarto entidade = service.consultaPorId(id);
        RespostaQuartoDTO dto = mapper.toDTO(entidade);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<RespostaQuartoDTO>> pesquisaFiltrada(
            @RequestParam(value = "tipo-quarto", required = false) String tipoQuarto,
            @RequestParam(value = "p1", required = false) BigDecimal p1,
            @RequestParam(value = "p2", required = false) BigDecimal p2,
            @RequestParam(value = "nome-hotel", required = false) String nomeHotel
            ) {
        List<Quarto> resultado = service.pesquisaFiltrada(tipoQuarto, p1, p2, nomeHotel);
        List<RespostaQuartoDTO> resultadoDTO = mapper.toDTOList(resultado);

        return ResponseEntity.ok(resultadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody @Valid CadastroQuartoDTO dto) {
        Quarto entidade = mapper.toEntity(dto);
        service.atualizar(id, entidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
