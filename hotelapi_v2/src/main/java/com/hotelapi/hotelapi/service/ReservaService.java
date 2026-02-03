package com.hotelapi.hotelapi.service;

import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.ReservaRepository;
import com.hotelapi.hotelapi.repository.specs.ReservaSpecs;
import com.hotelapi.hotelapi.validation.ReservaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaValidator validator;

    public void salvar(Reserva reserva) {
        validator.validar(reserva);
        repository.save(reserva);
    }

    public Reserva consultaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));
    }

    public List<Reserva> pesquisaFiltrada(String nomeHospede, String email, String telefone,
                                          String codigoConfimacao, String tipoQuarto, LocalDate d1, LocalDate d2) {

        Specification<Reserva> specs = (root, query, cb) -> cb.conjunction();

        if (nomeHospede != null) {
            specs = specs.and(ReservaSpecs.likeNomeHospede(nomeHospede));
        }

        if (email != null) {
            specs = specs.and(ReservaSpecs.likeEmail(email));
        }

        if (telefone != null) {
            specs = specs.and(ReservaSpecs.likeTelefone(telefone));
        }

        if (codigoConfimacao != null) {
            specs = specs.and(ReservaSpecs.likeCodigoConfirmacao(codigoConfimacao));
        }

        if (tipoQuarto != null) {
            specs = specs.and(ReservaSpecs.likeTipoQuarto(tipoQuarto));
        }

        if (d1 != null && d2 != null) {
            specs = specs.and(ReservaSpecs.datasConflitantes(d1, d2));
        }

        return repository.findAll(specs);
    }

    public void atualizar(Long id, Reserva aux) {
        Reserva entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));

        entidade.setNomeHospede(aux.getNomeHospede());
        entidade.setEmail(aux.getEmail());
        entidade.setTelefone(aux.getTelefone());
        entidade.setNumAdultos(aux.getNumAdultos());
        entidade.setNumCriancas(aux.getNumCriancas());
        entidade.setTotal(aux.getTotal());
        entidade.setCodigoConfirmacao(aux.getCodigoConfirmacao());
        entidade.setQuarto(aux.getQuarto());

        validator.validar(entidade);
        repository.save(entidade);
    }

    public void deletar(Long id) {
        Reserva entidade = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro para esse id!"));

        repository.delete(entidade);
    }
}
