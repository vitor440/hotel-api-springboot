package com.hotelapi.hotelapi.mapper;

import com.hotelapi.hotelapi.dto.UsuarioDTO;
import com.hotelapi.hotelapi.model.Usuario;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setLogin( dto.login() );
        usuario.setEmail( dto.email() );
        usuario.setSenha( dto.senha() );
        List<String> list = dto.roles();
        if ( list != null ) {
            usuario.setRoles( new ArrayList<String>( list ) );
        }

        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String login = null;
        String email = null;
        String senha = null;
        List<String> roles = null;

        login = usuario.getLogin();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        List<String> list = usuario.getRoles();
        if ( list != null ) {
            roles = new ArrayList<String>( list );
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO( login, email, senha, roles );

        return usuarioDTO;
    }
}
