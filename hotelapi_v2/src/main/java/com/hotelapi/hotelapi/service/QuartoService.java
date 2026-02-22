package com.hotelapi.hotelapi.service;

import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import com.hotelapi.hotelapi.repository.ReservaRepository;
import com.hotelapi.hotelapi.repository.specs.QuartoSpecs;
import com.hotelapi.hotelapi.validation.QuartoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuartoService {

    private final QuartoRepository repository;
    private final QuartoValidator validator;

    public void salvar(Quarto entidade) {
        validator.validar(entidade);
        repository.save(entidade);
    }

    public Quarto consultaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));
    }

    public Page<Quarto> pesquisaFiltrada(String tipoQuarto, BigDecimal preco1, BigDecimal preco2, String nomeHotel, Integer numPagina, Integer tamanhoPagina) {
        Specification<Quarto> specs = (root, query, cb) -> cb.conjunction();

        if(tipoQuarto != null) {
            specs = specs.and(QuartoSpecs.equalTipoQuarto(tipoQuarto));
        }

        if(preco1 != null && preco2 != null) {
            specs = specs.and(QuartoSpecs.precoEntre(preco1, preco2));
        }

        if (nomeHotel != null) {
            specs = specs.and(QuartoSpecs.equalNomeHotel(nomeHotel));
        }

        PageRequest pageRequest = PageRequest.of(numPagina, tamanhoPagina);
        return repository.findAll(specs, pageRequest);
    }

    public void atualizar(Long id, Quarto aux) {
        Quarto entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));

        entidade.setTipoQuarto(aux.getTipoQuarto());
        entidade.setPrecoDiaria(aux.getPrecoDiaria());
        entidade.setCapacidadeMaxima(aux.getCapacidadeMaxima());
        entidade.setVagasDisponiveis(aux.getVagasDisponiveis());
        entidade.setTamanho(aux.getTamanho());
        entidade.setHotel(aux.getHotel());

        validator.validar(entidade);
        repository.save(entidade);
    }

    public void deletar(Long id) {
        Quarto entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));
        repository.delete(entidade);
    }

    public List<Quarto> obterQuartosDisponiveis(String tipoQuarto, LocalDate checkIn, LocalDate checkOut) {
//        List<Quarto> quartos = repository.obterQuartosDisponiveis(tipoQuarto, checkIn, checkOut);
        Specification<Quarto> spec = QuartoSpecs.quartosDisponiveis(tipoQuarto, checkIn, checkOut);
        List<Quarto> quartos = repository.findAll(spec);

        return quartos;
    }
}
