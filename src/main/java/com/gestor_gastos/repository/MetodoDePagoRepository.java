package com.gestor_gastos.repository;

import com.gestor_gastos.entity.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago, Long> {
    /// agregar métodos de ser necesario
}
