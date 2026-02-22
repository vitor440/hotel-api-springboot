package com.hotelapi.hotelapi.repository;

import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {

}
