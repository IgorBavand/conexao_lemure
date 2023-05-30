package com.conexaolemure.conexaolemure.modulos.usuario.repository;

import com.conexaolemure.conexaolemure.modulos.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>,
        QuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailIgnoreCase(String email);

}
