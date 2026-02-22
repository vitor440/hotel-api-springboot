package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.UsuarioDTO;
import com.hotelapi.hotelapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario usuario);
}
