package com.conexaolemure.conexaolemure.modulos.usuario.service;

import com.conexaolemure.conexaolemure.modulos.comum.exceptions.ConflictException;
import com.conexaolemure.conexaolemure.modulos.comum.exceptions.NotFoundException;
import com.conexaolemure.conexaolemure.modulos.usuario.dto.filter.UsuarioFiltros;
import com.conexaolemure.conexaolemure.modulos.usuario.dto.request.UsuarioRequest;
import com.conexaolemure.conexaolemure.modulos.usuario.dto.response.UsuarioResponse;
import com.conexaolemure.conexaolemure.modulos.usuario.model.Role;
import com.conexaolemure.conexaolemure.modulos.usuario.model.Usuario;
import com.conexaolemure.conexaolemure.modulos.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final String USUARIO_NAO_ENCONTRADO = "Usuario não encontrado";

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponse createUser(UsuarioRequest request) {
        checkUser(request.getEmail());
        var user = Usuario.of(request);
        Set<Role> roles = new HashSet<>();

        request.getRoles()
                .forEach(role -> roles.add(new Role(role)));

        user.setSenha(passwordEncoder.encode(request.getSenha()));
        user.setRoles(roles);

        return UsuarioResponse.of(usuarioRepository.save(user));
    }

    @Transactional
    public UsuarioResponse updateUser(Integer id, UsuarioRequest request) {
        checkUser(request.getEmail());
        var user = findById(id);
        BeanUtils.copyProperties(user, request, "id");

        Set<Role> roles = new HashSet<>();
        request.getRoles()
                .forEach(role -> roles.add(new Role(role)));

        user.setRoles(roles);
        return UsuarioResponse.of(user);
    }

    public Page<UsuarioResponse> getAll(PageRequest pageRequest, UsuarioFiltros filter) {
        return usuarioRepository.findAll(filter.toPredicate(), pageRequest).map(UsuarioResponse::of);
    }

    public UsuarioResponse getById(Integer id) {
        return UsuarioResponse.of(findById(id));
    }

    public UsuarioResponse deleteUser(Integer id) {
        var user = findById(id);

        usuarioRepository.delete(user);
        return UsuarioResponse.of(user);
    }

    private Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USUARIO_NAO_ENCONTRADO));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(USUARIO_NAO_ENCONTRADO));
    }

    private void checkUser(String email) {
        if(usuarioRepository.existsByEmailIgnoreCase(email)) {
            throw new ConflictException("Email já cadastrado!");
        }
    }

}