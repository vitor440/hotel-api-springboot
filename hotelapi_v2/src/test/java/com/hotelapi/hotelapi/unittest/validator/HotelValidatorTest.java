package com.hotelapi.hotelapi.unittest.validator;

import com.hotelapi.hotelapi.exception.RegistroDuplicadoException;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.repository.HotelRepository;
import com.hotelapi.hotelapi.unittest.mock.HotelMock;
import com.hotelapi.hotelapi.validation.HotelValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelValidatorTest {

    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    HotelValidator hotelValidator;

    HotelMock hotelMock;

    @BeforeEach
    void setUp() {
        hotelMock = new HotelMock();
    }

    @Test
    void devePermitirSalvarHotelQuandoNaoExisteDuplicidade() {
        // cenário
        Hotel hotelParaSalvar = hotelMock.mockEntidade(1);
        hotelParaSalvar.setId(null);
        when(hotelRepository
                        .findByNomeAndEstadoIgnoreCase(hotelParaSalvar.getNome(), hotelParaSalvar.getEstado()))
                .thenReturn(Optional.empty());

        // execução e verificação
        assertDoesNotThrow(() -> hotelValidator.validar(hotelParaSalvar));
    }

    @Test
    void deveLancarExcecaoAoSalvarHotelComNomeEEstadoDuplicados() {
        // cenário
        Hotel hotelParaSalvar = hotelMock.mockEntidade(1);
        hotelParaSalvar.setId(null);

        Hotel hotelEncontrado = hotelMock.mockEntidade(1);

        when(hotelRepository
                .findByNomeAndEstadoIgnoreCase(hotelParaSalvar.getNome(), hotelParaSalvar.getEstado()))
                .thenReturn(Optional.of(hotelEncontrado));

        // execução
        var erro = catchThrowable(() -> hotelValidator.validar(hotelParaSalvar));

        // verificação
        assertThat(erro)
                .isInstanceOf(RegistroDuplicadoException.class)
                .hasMessage("Não é permitido o cadastro de hotel com nome e estado já existente!");
    }

    @Test
    void devePermitirAtualizarHotelQuandoNaoExisteDuplicidade() {
        // cenário
        Hotel hotelComDadosAtualizados = hotelMock.mockEntidade(1);
        when(hotelRepository
                .findByNomeAndEstadoIgnoreCase(hotelComDadosAtualizados.getNome(),
                        hotelComDadosAtualizados.getEstado()))
                .thenReturn(Optional.empty());

        // execução e verificação
        assertDoesNotThrow(() -> hotelValidator.validar(hotelComDadosAtualizados));
    }

    @Test
    void deveNaoLancarExcecaoQuandoHotelDuplicadoTemMesmoIdd() {
        // cenário
        Hotel hotelComDadosAtualizados = hotelMock.mockEntidade(1);
        Hotel hotelExistente = hotelMock.mockEntidade(1);

        when(hotelRepository
                .findByNomeAndEstadoIgnoreCase(hotelComDadosAtualizados.getNome(),
                        hotelComDadosAtualizados.getEstado()))
                .thenReturn(Optional.of(hotelExistente));

        // execução e verificação
        assertDoesNotThrow(() -> hotelValidator.validar(hotelComDadosAtualizados));
    }

    @Test
    void deveLancarExcecaoAoAtualizarHotelComNomeEEstadoDuplicadosDeOutroRegistro() {
        // cenário
        Hotel hotelComDadosAtualizados = hotelMock.mockEntidade(1);
        Hotel hotelExistente = hotelMock.mockEntidade(2);

        when(hotelRepository
                .findByNomeAndEstadoIgnoreCase(hotelComDadosAtualizados.getNome(),
                        hotelComDadosAtualizados.getEstado()))
                .thenReturn(Optional.of(hotelExistente));

        // execução
        var erro = catchThrowable(() -> hotelValidator.validar(hotelComDadosAtualizados));

        // verificação
        assertThat(erro).isInstanceOf(RegistroDuplicadoException.class)
                .hasMessage("Não é permitido o cadastro de hotel com nome e estado já existente!");
    }
}
