package com.hotelapi.hotelapi.dto;

import java.math.BigDecimal;

public record RespostaQuartoDTO(Long id, String tipoQuarto, BigDecimal preco, Integer capacidade,
                                Integer disponivel, String tamanho, HotelDTO hotel) {
}
