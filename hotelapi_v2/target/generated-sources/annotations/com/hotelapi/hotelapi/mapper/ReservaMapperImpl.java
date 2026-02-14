package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.CadastroReservaDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.dto.RespostaReservaDTO;
import com.hotelapi.hotelapi.model.Reserva;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T19:27:38-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (JetBrains s.r.o.)"
)
@Component
public class ReservaMapperImpl extends ReservaMapper {

    @Autowired
    private QuartoMapper quartoMapper;

    @Override
    public RespostaReservaDTO toDTO(Reserva entidade) {
        if ( entidade == null ) {
            return null;
        }

        Long id = null;
        LocalDate checkIn = null;
        LocalDate checkOut = null;
        String nomeHospede = null;
        String email = null;
        String telefone = null;
        Integer quantidadeAdultos = null;
        Integer quantidadeCriancas = null;
        Integer totalHospedes = null;
        String codigoConfirmacao = null;
        RespostaQuartoDTO quarto = null;

        id = entidade.getId();
        checkIn = entidade.getCheckIn();
        checkOut = entidade.getCheckOut();
        nomeHospede = entidade.getNomeHospede();
        email = entidade.getEmail();
        telefone = entidade.getTelefone();
        quantidadeAdultos = entidade.getQuantidadeAdultos();
        quantidadeCriancas = entidade.getQuantidadeCriancas();
        totalHospedes = entidade.getTotalHospedes();
        codigoConfirmacao = entidade.getCodigoConfirmacao();
        quarto = quartoMapper.toDTO( entidade.getQuarto() );

        RespostaReservaDTO respostaReservaDTO = new RespostaReservaDTO( id, checkIn, checkOut, nomeHospede, email, telefone, quantidadeAdultos, quantidadeCriancas, totalHospedes, codigoConfirmacao, quarto );

        return respostaReservaDTO;
    }

    @Override
    public Reserva toEntity(CadastroReservaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setQuantidadeAdultos( dto.quantidadeAdultos() );
        reserva.setQuantidadeCriancas( dto.quantidadeCriancas() );
        reserva.setCheckIn( dto.checkIn() );
        reserva.setCheckOut( dto.checkOut() );
        reserva.setNomeHospede( dto.nomeHospede() );
        reserva.setEmail( dto.email() );
        reserva.setTelefone( dto.telefone() );

        reserva.setQuarto( repository.findById(dto.idQuarto()).orElse(null) );

        return reserva;
    }

    @Override
    public List<RespostaReservaDTO> toDTOList(List<Reserva> lista) {
        if ( lista == null ) {
            return null;
        }

        List<RespostaReservaDTO> list = new ArrayList<RespostaReservaDTO>( lista.size() );
        for ( Reserva reserva : lista ) {
            list.add( toDTO( reserva ) );
        }

        return list;
    }

    @Override
    public List<Reserva> toEntityList(List<CadastroReservaDTO> listaDTO) {
        if ( listaDTO == null ) {
            return null;
        }

        List<Reserva> list = new ArrayList<Reserva>( listaDTO.size() );
        for ( CadastroReservaDTO cadastroReservaDTO : listaDTO ) {
            list.add( toEntity( cadastroReservaDTO ) );
        }

        return list;
    }
}
