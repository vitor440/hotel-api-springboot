package com.hotelapi.hotelapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CadastroQuartoDTO(Long id,
                                @NotBlank(message = "Campo Obrigatório!")
                                String tipoQuarto,
                                @NotNull(message = "Campo Obrigatório!")
                                BigDecimal preco,
                                @NotNull(message = "Campo Obrigatório!")
                                @Max(value = 5, message = "Capacidade máxima maior que 5!")
                                Integer capacidade,
                                @NotNull(message = "Campo Obrigatório!")
                                Integer disponivel,
                                @NotBlank(message = "Campo Obrigatório!")
                                String tamanho,
                                @NotNull(message = "Campo Obrigatório!")
                                Long idHotel) {
}
