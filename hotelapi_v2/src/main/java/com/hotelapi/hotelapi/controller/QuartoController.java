package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.mapper.QuartoMapper;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.service.QuartoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/quartos")
@RequiredArgsConstructor
public class QuartoController {

    private final QuartoService service;
    private final QuartoMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<RespostaQuartoDTO> consultaPorId(@PathVariable("id") Long id) {
        Quarto entidade = service.consultaPorId(id);
        RespostaQuartoDTO dto = mapper.toDTO(entidade);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<RespostaQuartoDTO>> pesquisaFiltrada(
            @RequestParam(value = "tipo-quarto", required = false) String tipoQuarto,
            @RequestParam(value = "p1", required = false) BigDecimal p1,
            @RequestParam(value = "p2", required = false) BigDecimal p2,
            @RequestParam(value = "nome-hotel", required = false) String nomeHotel,
            @RequestParam(value = "num-pagina", defaultValue = "0") Integer numPagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "3") Integer tamanhoPagina
            ) {
        Page<Quarto> resultado = service.pesquisaFiltrada(tipoQuarto, p1, p2, nomeHotel, numPagina, tamanhoPagina);
        Page<RespostaQuartoDTO> resultadoDTO = resultado.map(mapper::toDTO);

        return ResponseEntity.ok(resultadoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody @Valid CadastroQuartoDTO dto) {
        Quarto entidade = mapper.toEntity(dto);
        service.atualizar(id, entidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<RespostaQuartoDTO>> obterQuartosDisponiveis(
            @RequestParam(value = "tipo-quarto") String tipoQuarto,
            @RequestParam(value = "check-in") LocalDate checkIn,
            @RequestParam(value = "check-out") LocalDate checkOut
    ) {
        List<Quarto> quartosDisponiveis = service.obterQuartosDisponiveis(tipoQuarto, checkIn, checkOut);
        List<RespostaQuartoDTO> quartosDisponiveisDTO = mapper.toDTOList(quartosDisponiveis);

        return ResponseEntity.ok(quartosDisponiveisDTO);
    }
}
