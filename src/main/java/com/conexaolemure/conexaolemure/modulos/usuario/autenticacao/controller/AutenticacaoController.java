package com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.controller;

import com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.dto.LoginRequest;
import com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.dto.response.JwtPayloadResponse;
import com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.service.CustomAuthenticationProvider;
import com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final TokenService tokenService;

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @PostMapping("auth/token")
    public JwtPayloadResponse token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
        Authentication authentication = customAuthenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getSenha()));

        return tokenService.gerarToken(authentication);
    }

}
