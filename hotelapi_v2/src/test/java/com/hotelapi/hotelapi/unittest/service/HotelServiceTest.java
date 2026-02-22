package com.hotelapi.hotelapi.unittest.service;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.exception.RegistroDuplicadoException;
import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.repository.HotelRepository;
import com.hotelapi.hotelapi.service.HotelService;
import com.hotelapi.hotelapi.unittest.mock.HotelMock;
import com.hotelapi.hotelapi.validation.HotelValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    HotelRepository hotelRepository;

    @Mock
    HotelValidator hotelValidator;

    @InjectMocks
    HotelService service;

    HotelMock hotelMock;

    @BeforeEach
    void setUp() {
        hotelMock = new HotelMock();
    }


    @Test
    void deveSalvarHotelComSucesso() {
        // cenário
        Hotel hotel = hotelMock.mockEntidade(1);
        doNothing().when(hotelValidator).validar(hotel);
        // execução
        service.salvar(hotel);
        // verificação
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    void deveDarErroAoSalvarHotelDuplicado() {
        // cenário
        Hotel hotel = hotelMock.mockEntidade(1);
        doThrow(RegistroDuplicadoException.class).when(hotelValidator).validar(hotel);

        // execução
        var erro = catchThrowable(() -> service.salvar(hotel));

        // verificação
        assertThat(erro).isInstanceOf(RegistroDuplicadoException.class);
        verify(hotelRepository, times(0)).save(hotel);
    }

    @Test
    void deveObterUmHotelPorId() {
        //cenário
        Hotel hotel = hotelMock.mockEntidade(1);
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        // execução
        Hotel hotelEncontrado = service.consultaPorId(1L);

        // verificação
        assertThat(hotelEncontrado.getCidade()).isEqualTo(hotel.getCidade());
        assertThat(hotelEncontrado.getNome()).isEqualTo(hotel.getNome());
        assertThat(hotelEncontrado.getAndares()).isEqualTo(hotel.getAndares());
        assertThat(hotelEncontrado.getEstado()).isEqualTo(hotel.getEstado());
    }

    @Test
    void deveDarErroAoConsultarUmHotelComIdInexistente() {

        // cenário
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // execução
        var erro = catchThrowable(() -> service.consultaPorId(1L));

        // verificação
        assertThat(erro).isInstanceOf(RegistroNaoEncontradoException.class)
                .hasMessage("Não existe um registro com esse id!");

    }

    @Test
    void deveAtualizarHotelComSucesso() {
        // cenário
        Hotel hotel = new Hotel();
        HotelDTO dto = hotelMock.mockDTO(1);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        doNothing().when(hotelValidator).validar(hotel);

        // execução
        service.atualizar(1L, dto);

        // verificação
        assertThat(hotel.getNome()).isEqualTo(dto.nome());
        assertThat(hotel.getEstado()).isEqualTo(dto.estado());
        assertThat(hotel.getCidade()).isEqualTo(dto.cidade());
        assertThat(hotel.getAndares()).isEqualTo(dto.andares());
        assertThat(hotel.getTelefone()).isEqualTo(dto.telefone());

        verify(hotelValidator, times(1)).validar(hotel);
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    void deveDarErroAoTentarAtualizarUmHotelComIdInexistente() {

        // cenário
        HotelDTO dto = hotelMock.mockDTO(1);
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // execução
        var erro = catchThrowable(() -> service.atualizar(1L, dto));

        // verificação
        assertThat(erro).isInstanceOf(RegistroNaoEncontradoException.class)
                .hasMessage("Não existe um registro com esse id!");

        verify(hotelValidator, times(0)).validar(any(Hotel.class));
        verify(hotelRepository, times(0)).save(any(Hotel.class));
    }

    @Test
    void deveLancarRegistroDuplicadoExceptionAoTentarAtualizarHotel() {
        // cenário
        Hotel hotel = new Hotel();
        HotelDTO dto = hotelMock.mockDTO(1);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));
        doThrow(RegistroDuplicadoException.class).when(hotelValidator).validar(hotel);

        // execução
        var erro = catchThrowable(() -> service.atualizar(1L, dto));

        // verificação
        assertThat(erro).isInstanceOf(RegistroDuplicadoException.class);

        verify(hotelValidator, times(1)).validar(hotel);
        verify(hotelRepository, times(0)).save(hotel);
    }

    @Test
    void deveDeletarUmHotelComSucesso() {
        // cenário
        Hotel hotel = hotelMock.mockEntidade(1);
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        // execução
        service.deletar(1L);

        // verificação
        verify(hotelRepository, times(1)).delete(hotel);
    }

    @Test
    void deveDarErroAoDeletarUmHotelComIdInexistente() {
        // cenário
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // execução
        var erro = catchThrowable(() -> service.deletar(1L));

        // verificação
        assertThat(erro).isInstanceOf(RegistroNaoEncontradoException.class)
                .hasMessage("Não existe um registro com esse id!");
        verify(hotelRepository, times(0)).delete(any(Hotel.class));
    }

}
