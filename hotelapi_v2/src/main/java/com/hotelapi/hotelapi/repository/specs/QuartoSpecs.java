package com.hotelapi.hotelapi.repository.specs;

import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.model.Reserva;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public static Specification<Quarto> quartosDisponiveis(String tipoQuarto, LocalDate checkIn, LocalDate checkOut) {
        return (root, query, cb) -> {

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Reserva> reservas = subquery.from(Reserva.class);

            // equivalente = select r.id_quarto from reserva r where (r.checkOut >= :checkIn) and (r.checkIn <= :checkOut)
            // subquery que retorna todos os id_quarto da tabela "reservas" se as datas conflitarem com os check-in e check-out especificados.
            Subquery<Long> buscaPorDatasConflitantes = subquery.select(reservas.get("quarto").get("id"))
                    .where(cb.and(cb.lessThanOrEqualTo(reservas.get("checkIn"), checkOut),
                            cb.greaterThanOrEqualTo(reservas.get("checkOut"), checkIn)
                    ));

            return cb.and(cb.like(root.get("tipoQuarto"), tipoQuarto), cb.not(root.get("id").in(buscaPorDatasConflitantes)));
        };
    }
}
