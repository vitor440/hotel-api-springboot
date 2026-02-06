package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.CadastroReservaDTO;
import com.hotelapi.hotelapi.dto.RespostaReservaDTO;
import com.hotelapi.hotelapi.mapper.ReservaMapper;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;
    private final ReservaMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CadastroReservaDTO dto) {
        Reserva entidade = mapper.toEntity(dto);
        service.salvar(entidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaReservaDTO> consultaPorId(@PathVariable("id") Long id) {
        Reserva entidade = service.consultaPorId(id);
        RespostaReservaDTO dto = mapper.toDTO(entidade);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<RespostaReservaDTO>> pesquisaFiltrada(
            @RequestParam(value = "nome-hospede", required = false) String nomeHospede,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "telefone", required = false) String telefone,
            @RequestParam(value = "tipo-quarto", required = false) String tipoQuarto,
            @RequestParam(value = "check-in", required = false) LocalDate checkIn,
            @RequestParam(value = "check-out", required = false) LocalDate checkOut,
            @RequestParam(value = "num-pagina", defaultValue = "0") Integer numPagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "3") Integer tamanhoPagina
    ) {
        Page<Reserva> resultado = service
                .pesquisaFiltrada(nomeHospede, email, telefone, tipoQuarto, checkIn, checkOut, numPagina, tamanhoPagina);

        Page<RespostaReservaDTO> resultadoDTO = resultado.map(mapper::toDTO);
        return ResponseEntity.ok(resultadoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody CadastroReservaDTO dto) {
        Reserva entidade = mapper.toEntity(dto);
        service.atualizar(id, entidade);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
