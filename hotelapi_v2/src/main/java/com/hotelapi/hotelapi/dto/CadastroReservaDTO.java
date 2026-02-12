package com.hotelapi.hotelapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroReservaDTO(@NotNull(message = "campo obrigatório!")
                                 @Future(message = "não pode ser data futura!")
                                 LocalDate checkIn,
                                 @NotNull(message = "campo obrigatório!")
                                 @Future(message = "não pode ser data futura!")
                                 LocalDate checkOut,
                                 @NotBlank(message = "campo obrigatório!")
                                 String nomeHospede,
                                 @NotBlank(message = "campo obrigatório!")
                                 String email,
                                 @NotBlank(message = "campo obrigatório!")
                                 String telefone,
                                 @NotNull(message = "campo obrigatório!")
                                 Integer quantidadeAdultos,
                                 @NotNull(message = "campo obrigatório!")
                                 Integer quantidadeCriancas,
                                 @NotNull(message = "campo obrigatório!")
                                 Integer totalHospedes,
                                 @NotBlank(message = "campo obrigatório!")
                                 String codigoConfirmacao,
                                 @NotNull(message = "campo obrigatório!")
                                 Long idQuarto) {
}
