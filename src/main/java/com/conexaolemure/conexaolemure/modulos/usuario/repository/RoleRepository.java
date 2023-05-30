package com.conexaolemure.conexaolemure.modulos.usuario.repository;

import com.conexaolemure.conexaolemure.modulos.usuario.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
