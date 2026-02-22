package com.hotelapi.hotelapi.repository;

import com.hotelapi.hotelapi.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Long>, JpaSpecificationExecutor<Quarto> {

    @Query(" SELECT q from Quarto q " +
            " WHERE q.tipoQuarto = :tipoQuarto" + " AND " +
            " q.id NOT IN ( " +
            " SELECT r.quarto.id from Reserva r" +
            " WHERE (r.checkIn <= :checkOut) AND (r.checkOut >= :checkIn)) ")
    List<Quarto> obterQuartosDisponiveis(String tipoQuarto, LocalDate checkIn, LocalDate checkOut);
}
