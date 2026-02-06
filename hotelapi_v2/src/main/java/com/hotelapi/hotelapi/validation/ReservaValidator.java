package com.hotelapi.hotelapi.validation;

import com.hotelapi.hotelapi.exception.DatasConflitantesException;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.ReservaRepository;
import com.hotelapi.hotelapi.repository.specs.ReservaSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservaValidator {

    private final ReservaRepository reservaRepository;

    public void validar(Reserva reserva) {
        if(datasConflitantes(reserva)) {
            throw new DatasConflitantesException("Não é possível fazer o registro de reservas com as datas especificadas!");
        }
    }

    private boolean datasConflitantes(Reserva reserva) {
        Specification<Reserva> specs = ReservaSpecs
                .datasConflitantes(reserva.getCheckIn(), reserva.getCheckOut())
                .and(ReservaSpecs.equalQuarto(reserva.getQuarto()));

        List<Reserva> lista = reservaRepository.findAll(specs);

        if(reserva.getId() == null) {
            return !lista.isEmpty();
        }

        return lista.stream().map(Reserva::getId).anyMatch(id -> !id.equals(reserva.getId()));
    }
}

