package com.hotelapi.hotelapi.service;

import com.hotelapi.hotelapi.exception.RegistroNaoEncontradoException;
import com.hotelapi.hotelapi.model.Usuario;
import com.hotelapi.hotelapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public void salvar(Usuario usuario) {
        var senha = usuario.getSenha();
        usuario.setSenha(encoder.encode(senha));
        repository.save(usuario);
    }

    public Usuario consultaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public void atualizar(Long id, Usuario aux) {
        Usuario usuarioExistente = repository.findById(id)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));

        usuarioExistente.setLogin(aux.getLogin());
        usuarioExistente.setEmail(aux.getEmail());
        usuarioExistente.setSenha(encoder.encode(aux.getSenha()));
        usuarioExistente.setRoles(aux.getRoles());

        repository.save(usuarioExistente);
    }

    public void deletarPorId(Long id) {
        Usuario usuarioExistente = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Não existe um registro com esse id!"));

        repository.delete(usuarioExistente);
    }

    public Usuario findByLogin(String login) {
        return repository.findByLogin(login);
    }
}
