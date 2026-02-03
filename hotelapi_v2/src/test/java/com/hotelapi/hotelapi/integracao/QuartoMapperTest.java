package com.hotelapi.hotelapi.integracao;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.CadastroReservaDTO;
import com.hotelapi.hotelapi.dto.RespostaReservaDTO;
import com.hotelapi.hotelapi.mapper.QuartoMapper;
import com.hotelapi.hotelapi.mapper.ReservaMapper;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.HotelRepository;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class QuartoMapperTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    @Autowired
    private QuartoMapper mapper;

    @Autowired
    private ReservaMapper mapper2;

    @Test
    void teste() {
        CadastroQuartoDTO dto = new CadastroQuartoDTO(1L, "Suite Royal", BigDecimal.valueOf(300),
                4, 12, "38m²", 1L);

        Quarto entidade = mapper.toEntity(dto);
        System.out.println(entidade);
    }

    @Test
    void teste2(){

        Reserva reserva = new Reserva();
        reserva.setId(2L);
        reserva.setCheckIn(LocalDate.of(2000, 1, 1));
        reserva.setCheckOut(LocalDate.of(2000, 1, 1));
        reserva.setNomeHospede("dsfdfsdfdsf");
        reserva.setEmail("343rfedfe");
        reserva.setNumAdultos(2);
        reserva.setNumCriancas(0);
        reserva.setTotal(2);
        reserva.setCodigoConfirmacao("234refdsfdsf");
        reserva.setQuarto(quartoRepository.findById(2L).orElse(null));

        RespostaReservaDTO dto = mapper2.toDTO(reserva);
        System.out.println(dto);
    }
}
