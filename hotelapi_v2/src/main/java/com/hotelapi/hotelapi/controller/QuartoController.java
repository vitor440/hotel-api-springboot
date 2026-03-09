package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.mapper.QuartoMapper;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.service.QuartoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Salvar", description = "Cadastrar novo quarto.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Salvo com sucesso."),
            @ApiResponse(responseCode = "409", description = "Quarto duplicado."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
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
    @Operation(summary = "Consulta pelo id", description = "Obtém um quarto pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe quarto com esse id.")
    })
    public ResponseEntity<RespostaQuartoDTO> consultaPorId(@PathVariable("id") Long id) {
        Quarto entidade = service.consultaPorId(id);
        RespostaQuartoDTO dto = mapper.toDTO(entidade);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Pesquisa", description = "Pesquisa quartos por parâmetros.")
    @ApiResponse(responseCode = "200", description = "Sucesso.")
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
    @Operation(summary = "Atualizar", description = "Atualizar quarto.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe quarto com esse id."),
            @ApiResponse(responseCode = "409", description = "Quarto duplicado."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody @Valid CadastroQuartoDTO dto) {
        Quarto entidade = mapper.toEntity(dto);
        service.atualizar(id, entidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar", description = "Deletar quarto pelo id.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe quarto com esse id.")
    })
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponiveis")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(summary = "Obter quartos disponíveis", description = "Obtém quartos disponíveis no intervalo de datas especificado.")
    @ApiResponse(responseCode = "200", description = "Sucesso.")
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
