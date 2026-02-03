package com.hotelapi.hotelapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HotelDTO(Long id,
                       @NotBlank(message = "Campo Obrigatório")
                       String nome,
                       @NotBlank(message = "Campo Obrigatório")
                       String estado,
                       @NotBlank(message = "Campo Obrigatório")
                       String cidade,
                       String telefone,
                       @NotNull(message = "Campo Obrigatório")
                       Integer andares) {
}
