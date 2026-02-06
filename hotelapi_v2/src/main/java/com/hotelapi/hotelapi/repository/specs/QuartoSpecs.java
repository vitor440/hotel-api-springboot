package com.hotelapi.hotelapi.repository.specs;

import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.model.Quarto;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class QuartoSpecs {

    public static Specification<Quarto> equalTipoQuarto(String tipoQuarto) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("tipoQuarto")), "%" + tipoQuarto.toUpperCase() + "%");
    }

    public static Specification<Quarto> precoEntre(BigDecimal p1, BigDecimal p2) {
        return (root, query, cb) -> cb.between(root.get("precoDiaria"), p1, p2);
    }

    public static Specification<Quarto> equalNomeHotel(String nomeHotel) {
        return (root, query, cb) -> {
            Join<Object, Object> hotel = root.join("hotel", JoinType.LEFT);
            return cb.like(cb.upper(hotel.get("nome")), "%" + nomeHotel.toUpperCase() + "%");
        };
    }

    public static Specification<Quarto> equalHotel(Hotel hotel) {
        return (root, query, cb) -> cb
                .equal(root.get("hotel"), hotel);
    }
}
