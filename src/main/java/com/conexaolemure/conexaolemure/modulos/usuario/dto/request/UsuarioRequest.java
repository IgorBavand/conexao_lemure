package com.conexaolemure.conexaolemure.modulos.usuario.dto.request;

import com.conexaolemure.conexaolemure.modulos.usuario.enums.ESituacao;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioRequest {
    private String nome;
    private String email;
    private String senha;
    private ESituacao situacao;
    private List<Integer> roles;
}