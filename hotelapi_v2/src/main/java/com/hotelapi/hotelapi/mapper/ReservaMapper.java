package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.CadastroReservaDTO;
import com.hotelapi.hotelapi.dto.RespostaReservaDTO;
import com.hotelapi.hotelapi.model.Reserva;
import com.hotelapi.hotelapi.repository.QuartoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = QuartoMapper.class)
public abstract class ReservaMapper {

    @Autowired
    QuartoRepository repository;

    public abstract RespostaReservaDTO toDTO(Reserva entidade);

    @Mapping(target = "quarto", expression = "java(repository.findById(dto.idQuarto()).orElse(null))")
    public abstract Reserva toEntity(CadastroReservaDTO dto);

    public abstract List<RespostaReservaDTO> toDTOList(List<Reserva> lista);

    public abstract List<Reserva> toEntityList(List<CadastroReservaDTO> listaDTO);
}
