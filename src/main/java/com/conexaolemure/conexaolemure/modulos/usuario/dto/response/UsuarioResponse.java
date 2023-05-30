package com.conexaolemure.conexaolemure.modulos.usuario.dto.response;

import com.conexaolemure.conexaolemure.modulos.usuario.enums.ESituacao;
import com.conexaolemure.conexaolemure.modulos.usuario.model.Usuario;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;
    private String situacao;
    private List<String> roles;
    private LocalDateTime dataCadastro;
    private LocalDateTime ultimaAtualizacao;

    public static UsuarioResponse of(Usuario usuario) {
        List<String> roles = new ArrayList<String>();
        usuario.getRoles().forEach(role -> roles.add(role.getNome()));

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .situacao(usuario.getSituacao())
                .roles(roles)
                .dataCadastro(usuario.getDataCadastro())
                .ultimaAtualizacao(usuario.getUltimaAtualizacao())
                .build();
    }
}