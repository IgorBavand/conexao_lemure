package com.conexaolemure.conexaolemure.modulos.usuario.autenticacao.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtPayloadResponse {

    private String user;
    private String token;
    private String authorities;
    private String expiration;
}