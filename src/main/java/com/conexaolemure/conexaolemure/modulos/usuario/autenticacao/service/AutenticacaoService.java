package com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.service;

import com.conexaolemure.conexaolemure.modulos.usuario.dto.response.UsuarioResponse;
import com.conexaolemure.conexaolemure.modulos.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioService usuarioService;

    public UsuarioResponse getUserAuthenticated() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = usuarioService.findByEmail(email);

        return UsuarioResponse.builder()
                .id(user.getId())
                .nome(user.getNome())
                .email(user.getEmail())
                .build();
    }
}