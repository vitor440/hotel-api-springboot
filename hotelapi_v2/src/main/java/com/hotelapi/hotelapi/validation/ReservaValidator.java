package com.hotelapi.hotelapi.validation;

import com.hotelapi.hotelapi.exception.CapacidadeMaximaException;
import com.hotelapi.hotelapi.exception.DatasConflitantesException;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.ReservaRepository;
import com.hotelapi.hotelapi.repository.specs.ReservaSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservaValidator {

    private final ReservaRepository reservaRepository;

    public void validar(Reserva reserva) {
        if(datasConflitantes(reserva)) {
            throw new DatasConflitantesException("O quarto não está disponível no período escolhido!");
        }

        if(capacidadeMaximaUltrapassada(reserva)) {
            throw new CapacidadeMaximaException("Capacidade máxima excedida!");
        }

        if(checkOutAnteriorACheckIn(reserva)) {
            throw new RuntimeException("A data de check-out não pode ser anterior à data de check-in!");
        }

        if(reserva.getQuarto().getVagasDisponiveis() == 0) {
            throw new RuntimeException("Não há vagas disponíveis para esse quarto!");
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

        return lista.stream()
                .map(Reserva::getId)
                .anyMatch(id -> !id.equals(reserva.getId()));
    }

    private boolean capacidadeMaximaUltrapassada(Reserva reserva) {
        Quarto quarto = reserva.getQuarto();
        return reserva.getTotalHospedes() > quarto.getCapacidadeMaxima() ? true : false;
    }

    private boolean checkOutAnteriorACheckIn(Reserva reserva) {
        return reserva.getCheckOut().isBefore(reserva.getCheckIn());
    }


}

