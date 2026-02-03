package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.model.Hotel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    Hotel toEntity(HotelDTO dto);

    HotelDTO toDTO(Hotel entidade);

    List<Hotel> toEntityList(List<HotelDTO> dtos);

    List<HotelDTO> toDTOList(List<Hotel> entidades);
}
