package com.hotelapi.hotelapi.unittest.mapper;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.mapper.HotelMapper;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.unittest.mock.HotelMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class HotelMapperTest {

    private HotelMock mock;
    private HotelMapper mapper = Mappers.getMapper(HotelMapper.class);

    @BeforeEach
    void setUp() {
        mock = new HotelMock();
    }

    @Test
    void dtoParaEntidade() {
        Hotel entidade = mapper.toEntity(mock.mockDTO(4));
        assertEquals(entidade.getId(), 4L);
        assertEquals(entidade.getNome(), "Nome Hotel4");
        assertEquals(entidade.getEstado(), "Estado4");
        assertEquals(entidade.getCidade(), "Cidade4");
        assertEquals(entidade.getTelefone(), "Telefone4");
        assertEquals(entidade.getAndares(), 4);
    }

    @Test
    void entidadeParaDTO() {
        HotelDTO dto = mapper.toDTO(mock.mockEntidade(8));
        assertEquals(dto.id(), 8L);
        assertEquals(dto.nome(), "Nome Hotel8");
        assertEquals(dto.estado(), "Estado8");
        assertEquals(dto.cidade(), "Cidade8");
        assertEquals(dto.telefone(), "Telefone8");
        assertEquals(dto.andares(), 8);
    }

    @Test
    void listaDeDtoParaEntidade() {
        List<Hotel> entidades = mapper.toEntityList(mock.mockListaDTO(10));

        Hotel entidade2 = entidades.get(2);
        assertEquals(entidade2.getId(), 2L);
        assertEquals(entidade2.getNome(), "Nome Hotel2");
        assertEquals(entidade2.getEstado(), "Estado2");
        assertEquals(entidade2.getCidade(), "Cidade2");
        assertEquals(entidade2.getTelefone(), "Telefone2");
        assertEquals(entidade2.getAndares(), 2);

        Hotel entidade5= entidades.get(5);
        assertEquals(entidade5.getId(), 5L);
        assertEquals(entidade5.getNome(), "Nome Hotel5");
        assertEquals(entidade5.getEstado(), "Estado5");
        assertEquals(entidade5.getCidade(), "Cidade5");
        assertEquals(entidade5.getTelefone(), "Telefone5");
        assertEquals(entidade5.getAndares(), 5);

        Hotel entidade7= entidades.get(7);
        assertEquals(entidade7.getId(), 7L);
        assertEquals(entidade7.getNome(), "Nome Hotel7");
        assertEquals(entidade7.getEstado(), "Estado7");
        assertEquals(entidade7.getCidade(), "Cidade7");
        assertEquals(entidade7.getTelefone(), "Telefone7");
        assertEquals(entidade7.getAndares(), 7);
    }

    @Test
    void listaDeEntidadeParaDTO() {
        List<HotelDTO> dtos = mapper.toDTOList(mock.mockListaEntidade(10));

        HotelDTO dto2 = dtos.get(2);
        assertEquals(dto2.id(), 2L);
        assertEquals(dto2.nome(), "Nome Hotel2");
        assertEquals(dto2.estado(), "Estado2");
        assertEquals(dto2.cidade(), "Cidade2");
        assertEquals(dto2.telefone(), "Telefone2");
        assertEquals(dto2.andares(), 2);

        HotelDTO dto5 = dtos.get(5);
        assertEquals(dto5.id(), 5L);
        assertEquals(dto5.nome(), "Nome Hotel5");
        assertEquals(dto5.estado(), "Estado5");
        assertEquals(dto5.cidade(), "Cidade5");
        assertEquals(dto5.telefone(), "Telefone5");
        assertEquals(dto5.andares(), 5);

        HotelDTO dto7 = dtos.get(7);
        assertEquals(dto7.id(), 7L);
        assertEquals(dto7.nome(), "Nome Hotel7");
        assertEquals(dto7.estado(), "Estado7");
        assertEquals(dto7.cidade(), "Cidade7");
        assertEquals(dto7.telefone(), "Telefone7");
        assertEquals(dto7.andares(), 7);
    }
}
