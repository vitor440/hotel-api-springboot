package com.hotelapi.hotelapi.repository;

import com.hotelapi.hotelapi.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByNomeAndEstadoIgnoreCase(String nome, String estado);
}
