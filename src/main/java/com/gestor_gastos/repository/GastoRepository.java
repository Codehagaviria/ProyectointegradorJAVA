package com.gestor_gastos.repository;

import com.gestor_gastos.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // agregar métodos de ser necesario como buscar, contar etc
}

