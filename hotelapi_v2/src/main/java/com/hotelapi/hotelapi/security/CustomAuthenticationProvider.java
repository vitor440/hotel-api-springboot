package com.hotelapi.hotelapi.security;

import com.hotelapi.hotelapi.model.Usuario;
import com.hotelapi.hotelapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senhaDigitada = (String) authentication.getCredentials();

        Usuario usuarioEncontrado = usuarioService.findByLogin(login);

        if(usuarioEncontrado == null) {
            throw usernameNotFoundException();
        }

        String senhaCriptografada = usuarioEncontrado.getSenha();

        boolean senhasBatem = encoder.matches(senhaDigitada, senhaCriptografada);

        if(senhasBatem) {
            return new CustomAuthentication(usuarioEncontrado);
        }

        throw usernameNotFoundException();
    }

    private static UsernameNotFoundException usernameNotFoundException() {
        return new UsernameNotFoundException("Login e/ou senha incorreto!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
