package com.hotelapi.hotelapi.controller;

import com.hotelapi.hotelapi.dto.CadastroReservaDTO;
import com.hotelapi.hotelapi.dto.RespostaReservaDTO;
import com.hotelapi.hotelapi.mapper.ReservaMapper;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.service.ReservaService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<RespostaReservaDTO>> pesquisaFiltrada(
            @RequestParam(value = "nome-hospede", required = false) String nomeHospede,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "telefone", required = false) String telefone,
            @RequestParam(value = "codigo-confirmacao", required = false) String codigoConfirmacao,
            @RequestParam(value = "tipo-quarto", required = false) String tipoQuarto,
            @RequestParam(value = "check-in", required = false) LocalDate checkIn,
            @RequestParam(value = "check-out", required = false) LocalDate checkOut
    ) {
        List<Reserva> resultado = service
                .pesquisaFiltrada(nomeHospede, email, telefone, codigoConfirmacao, tipoQuarto, checkIn, checkOut);

        List<RespostaReservaDTO> resultadoDTO = mapper.toDTOList(resultado);
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
