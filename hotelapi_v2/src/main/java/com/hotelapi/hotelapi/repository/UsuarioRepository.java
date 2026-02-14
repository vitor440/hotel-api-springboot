package com.hotelapi.hotelapi.repository;

import com.hotelapi.hotelapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    Usuario findByEmail(String email);
}
