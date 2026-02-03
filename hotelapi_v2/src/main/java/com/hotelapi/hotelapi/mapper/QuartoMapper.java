package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.CadastroQuartoDTO;
import com.hotelapi.hotelapi.dto.RespostaQuartoDTO;
import com.hotelapi.hotelapi.model.Quarto;
import com.hotelapi.hotelapi.repository.HotelRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Mapper(componentModel = "spring", uses = HotelMapper.class)
public abstract class QuartoMapper {

    @Autowired
    HotelRepository repository;

    public abstract RespostaQuartoDTO toDTO(Quarto entidade);

    // repository.findById(dto.idHotel()).orElse(null)
    // @Mapping(target = "hotel", expression = "java(repository.findById(dto.idHotel()).orElse(null))")
    // repository.findById(dto.idHotel()).orElseThrow(() -> new MethodArgumentNotValidException("Hotel inexistente!"))
    @Mapping(target = "hotel", expression = "java(repository.findById(dto.idHotel()).orElseThrow(() -> new RuntimeException(\"Hotel inexistente!\")))")
    public abstract Quarto toEntity(CadastroQuartoDTO dto);

    public abstract List<RespostaQuartoDTO> toDTOList(List<Quarto> lista);

    public abstract List<Quarto> toEntityList(List<CadastroQuartoDTO> listaDTO);
}
