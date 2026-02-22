package com.hotelapi.hotelapi.validation;

import com.hotelapi.hotelapi.exception.RegistroDuplicadoException;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HotelValidator {

    private final HotelRepository repository;

    public void validar(Hotel entidade) {
        if(existeRegistroDuplicado(entidade)) {
            throw new RegistroDuplicadoException("Já existe um hotel cadastrado com o mesmo nome neste estado.!");
        }
    }

    public boolean existeRegistroDuplicado(Hotel entidade) {
        Optional<Hotel> resultado = repository.findByNomeAndEstadoIgnoreCase(entidade.getNome(), entidade.getEstado());

        if(entidade.getId() == null) {
            return resultado.isPresent();
        }

        return resultado.map(Hotel::getId)
                .stream()
                .anyMatch(id -> !id.equals(entidade.getId()));
    }
}
