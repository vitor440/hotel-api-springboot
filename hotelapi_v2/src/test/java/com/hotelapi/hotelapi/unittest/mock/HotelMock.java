package com.hotelapi.hotelapi.unittest.mock;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.model.Hotel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HotelMock {

    public Hotel mockEntidade(Integer n) {
        Hotel h = new Hotel();
        h.setId((long) n);
        h.setNome("Nome Hotel" + n);
        h.setEstado("Estado" + n);
        h.setCidade("Cidade" + n);
        h.setTelefone("Telefone" + n);
        h.setAndares(n);

        return h;
    }

    public HotelDTO mockDTO(Integer n) {
        Long id = (long) n;
        String nome = "Nome Hotel" + n;
        String estado = "Estado" + n;
        String cidade = "Cidade" + n;
        String telefone = "Telefone" + n;
        Integer andares = n;
        HotelDTO dto = new HotelDTO(id, nome, estado, cidade, telefone, andares);

        return dto;
    }

    public List<Hotel> mockListaEntidade(Integer n) {
        List<Hotel> lista = new ArrayList<Hotel>();
        for(int i = 0; i < n; i++) {
            lista.add(mockEntidade(i));
        }
        return lista;
    }

    public List<HotelDTO> mockListaDTO(Integer n) {
        List<HotelDTO> lista = new ArrayList<HotelDTO>();
        for(int i = 0; i < n; i++) {
            lista.add(mockDTO(i));
        }
        return lista;
    }
}
