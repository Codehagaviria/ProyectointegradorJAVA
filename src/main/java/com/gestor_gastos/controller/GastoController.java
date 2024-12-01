package com.gestor_gastos.controller;

import com.gestor_gastos.entity.Gasto;
import com.gestor_gastos.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoRepository gastoRepository;

    // Obtener todos los gastos
    @GetMapping
    public List<Gasto> getAllGastos() {
        return gastoRepository.findAll();
    }

    // Obtener un gasto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable Long id) {
        Optional<Gasto> gasto = gastoRepository.findById(id);
        return gasto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo gasto
    @PostMapping
    public Gasto createGasto(@RequestBody Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    // Actualizar un gasto existente
    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable Long id, @RequestBody Gasto gastoDetails) {
        Optional<Gasto> gasto = gastoRepository.findById(id);
        if (gasto.isPresent()) {
            Gasto updatedGasto = gasto.get();
            updatedGasto.setDescripcion(gastoDetails.getDescripcion());
            updatedGasto.setMonto(gastoDetails.getMonto());
            updatedGasto.setFecha(gastoDetails.getFecha());
            updatedGasto.setCategoria(gastoDetails.getCategoria());
            gastoRepository.save(updatedGasto);
            return ResponseEntity.ok(updatedGasto);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un gasto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable Long id) {
        if (gastoRepository.existsById(id)) {
            gastoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
