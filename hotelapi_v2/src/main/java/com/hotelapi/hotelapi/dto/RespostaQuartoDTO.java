package com.hotelapi.hotelapi.dto;

import java.math.BigDecimal;

public record RespostaQuartoDTO(Long id,
                                String tipoQuarto,
                                BigDecimal precoDiaria,
                                Integer capacidadeMaxima,
                                Integer vagasDisponiveis,
                                String tamanho,
                                HotelDTO hotel) {
}
