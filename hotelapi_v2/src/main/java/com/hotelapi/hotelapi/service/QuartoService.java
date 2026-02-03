package com.hotelapi.hotelapi.service;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import com.hotelapi.hotelapi.repository.specs.QuartoSpecs;
import com.hotelapi.hotelapi.validation.QuartoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
    }

    public List<Quarto> pesquisaFiltrada(String tipoQuarto, BigDecimal preco1, BigDecimal preco2, String nomeHotel) {
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

        return repository.findAll(specs);
    }

    public void atualizar(Long id, Quarto aux) {
        Quarto entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));

        entidade.setTipoQuarto(aux.getTipoQuarto());
        entidade.setPreco(aux.getPreco());
        entidade.setCapacidade(aux.getCapacidade());
        entidade.setDisponivel(aux.getDisponivel());
        entidade.setTamanho(aux.getTamanho());
        entidade.setHotel(aux.getHotel());

        validator.validar(entidade);
        repository.save(entidade);
    }

    public void deletar(Long id) {
        Quarto entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
        repository.delete(entidade);
    }
}
