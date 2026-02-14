package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.model.Quarto;
import java.math.BigDecimal;
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
public class QuartoMapperImpl extends QuartoMapper {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public RespostaQuartoDTO toDTO(Quarto entidade) {
        if ( entidade == null ) {
            return null;
        }

        Long id = null;
        String tipoQuarto = null;
        BigDecimal precoDiaria = null;
        Integer capacidadeMaxima = null;
        Integer vagasDisponiveis = null;
        String tamanho = null;
        HotelDTO hotel = null;

        id = entidade.getId();
        tipoQuarto = entidade.getTipoQuarto();
        precoDiaria = entidade.getPrecoDiaria();
        capacidadeMaxima = entidade.getCapacidadeMaxima();
        vagasDisponiveis = entidade.getVagasDisponiveis();
        tamanho = entidade.getTamanho();
        hotel = hotelMapper.toDTO( entidade.getHotel() );

        RespostaQuartoDTO respostaQuartoDTO = new RespostaQuartoDTO( id, tipoQuarto, precoDiaria, capacidadeMaxima, vagasDisponiveis, tamanho, hotel );

        return respostaQuartoDTO;
    }

    @Override
    public Quarto toEntity(CadastroQuartoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Quarto quarto = new Quarto();

        quarto.setTipoQuarto( dto.tipoQuarto() );
        quarto.setPrecoDiaria( dto.precoDiaria() );
        quarto.setCapacidadeMaxima( dto.capacidadeMaxima() );
        quarto.setVagasDisponiveis( dto.vagasDisponiveis() );
        quarto.setTamanho( dto.tamanho() );

        quarto.setHotel( repository.findById(dto.idHotel()).orElseThrow(() -> new RuntimeException("Hotel inexistente!")) );

        return quarto;
    }

    @Override
    public List<RespostaQuartoDTO> toDTOList(List<Quarto> lista) {
        if ( lista == null ) {
            return null;
        }

        List<RespostaQuartoDTO> list = new ArrayList<RespostaQuartoDTO>( lista.size() );
        for ( Quarto quarto : lista ) {
            list.add( toDTO( quarto ) );
        }

        return list;
    }

    @Override
    public List<Quarto> toEntityList(List<CadastroQuartoDTO> listaDTO) {
        if ( listaDTO == null ) {
            return null;
        }

        List<Quarto> list = new ArrayList<Quarto>( listaDTO.size() );
        for ( CadastroQuartoDTO cadastroQuartoDTO : listaDTO ) {
            list.add( toEntity( cadastroQuartoDTO ) );
        }

        return list;
    }
}
