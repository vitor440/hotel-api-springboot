package com.hotelapi.hotelapi.repository.specs;

import com.hotelapi.hotelapi.model.Reserva;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ReservaSpecs {

    public static Specification<Reserva> likeNomeHospede(String nomeHospede) {
        return (root, query, cb) -> cb
                .like(cb.upper(root.get("nomeHospede")), "%" + nomeHospede.toUpperCase() + "%");
    }

    public static Specification<Reserva> likeEmail(String email) {
        return (root, query, cb) -> cb
                .like(cb.upper(root.get("email")), "%" + email.toUpperCase() + "%");
    }

    public static Specification<Reserva> likeTelefone(String telefone) {
        return (root, query, cb) -> cb
                .like(root.get("telefone"), telefone);
    }

    public static Specification<Reserva> likeCodigoConfirmacao(String codigoConfirmacao) {
        return (root, query, cb) -> cb
                .like(root.get("codigoConfirmacao"), codigoConfirmacao);
    }

    public static Specification<Reserva> likeTipoQuarto(String tipoQuarto) {
        return (root, query, cb) -> {
            Join<Object, Object> quarto = root.join("quarto", JoinType.INNER);
            return cb.like(cb.upper(quarto.get("tipoQuarto")), "%" + tipoQuarto.toUpperCase() + "%");
        };
    }


    public static Specification<Reserva> datasConflitantes(LocalDate d1, LocalDate d2) {
        return (root, query, cb) -> cb
                .and(cb.lessThanOrEqualTo(root.get("checkIn"), d2),
                cb.greaterThanOrEqualTo(root.get("checkOut"), d1)
                );
    }
    }


