package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.HotelDTO;
import com.hotelapi.hotelapi.model.Hotel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T19:27:38-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (JetBrains s.r.o.)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Override
    public Hotel toEntity(HotelDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setId( dto.id() );
        hotel.setNome( dto.nome() );
        hotel.setEstado( dto.estado() );
        hotel.setCidade( dto.cidade() );
        hotel.setTelefone( dto.telefone() );
        hotel.setAndares( dto.andares() );

        return hotel;
    }

    @Override
    public HotelDTO toDTO(Hotel entidade) {
        if ( entidade == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String estado = null;
        String cidade = null;
        String telefone = null;
        Integer andares = null;

        id = entidade.getId();
        nome = entidade.getNome();
        estado = entidade.getEstado();
        cidade = entidade.getCidade();
        telefone = entidade.getTelefone();
        andares = entidade.getAndares();

        HotelDTO hotelDTO = new HotelDTO( id, nome, estado, cidade, telefone, andares );

        return hotelDTO;
    }

    @Override
    public List<Hotel> toEntityList(List<HotelDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Hotel> list = new ArrayList<Hotel>( dtos.size() );
        for ( HotelDTO hotelDTO : dtos ) {
            list.add( toEntity( hotelDTO ) );
        }

        return list;
    }

    @Override
    public List<HotelDTO> toDTOList(List<Hotel> entidades) {
        if ( entidades == null ) {
            return null;
        }

        List<HotelDTO> list = new ArrayList<HotelDTO>( entidades.size() );
        for ( Hotel hotel : entidades ) {
            list.add( toDTO( hotel ) );
        }

        return list;
    }
}
