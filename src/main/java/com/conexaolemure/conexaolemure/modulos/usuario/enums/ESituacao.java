package com.conexaolemure.conexaolemure.modulos.usuario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ESituacao {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String descricao;
}
