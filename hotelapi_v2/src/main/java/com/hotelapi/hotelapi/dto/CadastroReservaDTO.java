package com.hotelapi.hotelapi.dto;

import java.time.LocalDate;

public record CadastroReservaDTO(Long id,
                                 LocalDate checkIn,
                                 LocalDate checkOut,
                                 String nomeHospede,
                                 String email,
                                 String telefone,
                                 Integer quantidadeAdultos,
                                 Integer quantidadeCriancas,
                                 Integer totalHospedes,
                                 String codigoConfirmacao,
                                 Long idQuarto) {
}
