package com.conexaolemure.conexaolemure.modulos.usuario.dto.filter;


import com.conexaolemure.conexaolemure.modulos.usuario.predicate.UsuarioPredicate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFiltros {

    private String nome;
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastroInicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastroFim;

    @JsonIgnore
    public BooleanBuilder toPredicate() {
        return new UsuarioPredicate()
                .comNome(this.nome)
                .comNome(this.email)
                .withDate(this.dataCadastroInicio, this.dataCadastroFim)
                .build();
    }


}