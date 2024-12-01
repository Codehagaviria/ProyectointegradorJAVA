package com.gestor_gastos.repository;

import com.gestor_gastos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Por ejemplo: Encuentra usuarios por correo
    // Optional<Usuario> findByCorreo(String correo);
}

