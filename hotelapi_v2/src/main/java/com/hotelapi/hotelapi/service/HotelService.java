package com.hotelapi.hotelapi.service;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Hotel;
import com.hotelapi.hotelapi.repository.HotelRepository;
import com.hotelapi.hotelapi.validation.HotelValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository repository;
    private final HotelValidator validator;

    public void salvar(Hotel entidade) {
        validator.validar(entidade);
        repository.save(entidade);
    }

    public Hotel consultaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
    }

    public List<Hotel> pesquisaFiltrada(String nome, String estado) {
        Hotel entidade = new Hotel();
        entidade.setNome(nome);
        entidade.setEstado(estado);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Hotel> example = Example.of(entidade, matcher);
        return repository.findAll(example);
    }

    public void atualizar(Long id, HotelDTO dto) {
        Hotel entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
        entidade.setNome(dto.nome());
        entidade.setEstado(dto.estado());
        entidade.setCidade(dto.cidade());
        entidade.setTelefone(dto.telefone());
        entidade.setAndares(dto.andares());

        validator.validar(entidade);
        repository.save(entidade);
    }

    public void deletar(Long id) {
        Hotel entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
        repository.delete(entidade);
    }
}
