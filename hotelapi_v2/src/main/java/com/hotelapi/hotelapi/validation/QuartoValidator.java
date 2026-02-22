package com.hotelapi.hotelapi.validation;

import com.hotelapi.hotelapi.exception.RegistroDuplicadoException;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import com.hotelapi.hotelapi.repository.specs.QuartoSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuartoValidator {

    private final QuartoRepository repository;

    public void validar(Quarto entidade) {
        if (quartoDuplicado(entidade)) {
            throw new RegistroDuplicadoException("Já existe um quarto deste tipo cadastrado para este hotel!");
        }
    }

    private boolean quartoDuplicado(Quarto entidade) {
        Specification<Quarto> specs = QuartoSpecs.equalTipoQuarto(entidade.getTipoQuarto())
                .and(QuartoSpecs.equalHotel(entidade.getHotel()));

        List<Quarto> resultado = repository.findAll(specs);

        if (entidade.getId() == null) {
            return !resultado.isEmpty();
        }

        return resultado.stream()
                .map(Quarto::getId)
                .anyMatch(id -> !id.equals(entidade.getId()));
    }
}
