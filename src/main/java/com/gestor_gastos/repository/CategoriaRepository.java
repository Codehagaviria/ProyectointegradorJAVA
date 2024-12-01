package com.gestor_gastos.repository;

import com.gestor_gastos.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // agregar métodos de ser necesario como buscar, contar etc
}


