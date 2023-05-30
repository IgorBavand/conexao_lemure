package com.conexaolemure.conexaolemure.modulos.usuario.predicate;

import com.querydsl.core.BooleanBuilder;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import com.conexaolemure.conexaolemure.modulos.usuario.model.QUsuario;

public class UsuarioPredicate {
    private final QUsuario usuario = QUsuario.usuario;

    private final BooleanBuilder builder;

    public UsuarioPredicate() {
        this.builder = new BooleanBuilder();
    }

    public UsuarioPredicate comNome(String name) {
        if (!StringUtils.isEmpty(name)) {
            builder.and(usuario.nome.likeIgnoreCase("%" + name + "%"));
        }
        return this;
    }

    public UsuarioPredicate comEmail(String email) {
        if (!StringUtils.isEmpty(email)) {
            builder.and(usuario.email.eq(email));
        }
        return this;
    }

    public UsuarioPredicate withDate(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            builder.and(usuario.dataCadastro.between(startDate.atStartOfDay(),
                    endDate.atTime(LocalTime.MAX)));
        }
        return this;
    }

    public BooleanBuilder build() {
        return this.builder;
    }

}