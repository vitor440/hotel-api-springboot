package com.hotelapi.hotelapi.dto;

import java.time.LocalDate;

public record CadastroReservaDTO(Long id,
                                 LocalDate checkIn,
                                 LocalDate checkOut,
                                 String nomeHospede,
                                 String email,
                                 String telefone,
                                 Integer numAdultos,
                                 Integer numCriancas,
                                 Integer total,
                                 String codigoConfirmacao,
                                 Long idQuarto) {
}
