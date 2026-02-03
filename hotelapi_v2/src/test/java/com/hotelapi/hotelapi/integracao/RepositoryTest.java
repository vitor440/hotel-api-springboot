package com.hotelapi.hotelapi.integracao;

import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import com.hotelapi.hotelapi.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Test
    void test() {

        Quarto quarto = quartoRepository.findById(13L).orElse(null);
        Quarto quarto2 = quartoRepository.findById(13L).orElse(null);

        System.out.println(quarto);
        System.out.println(quarto2);
        System.out.println(quarto.equals(quarto2));
    }
}
