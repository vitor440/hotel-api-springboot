package com.hotelapi.hotelapi.dto;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
