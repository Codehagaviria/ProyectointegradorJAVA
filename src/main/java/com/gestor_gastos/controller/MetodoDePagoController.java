package com.gestor_gastos.controller;

import com.gestor_gastos.entity.MetodoDePago;
import com.gestor_gastos.repository.MetodoDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodosdepago")  // Define la ruta base para este controlador
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;  // Repositorio para interactuar con la base de datos

    // Método GET para obtener todos los métodos de pago
    @GetMapping
    public List<MetodoDePago> getAllMetodosDePago() {
        return metodoDePagoRepository.findAll();  // Retorna todos los métodos de pago de la base de datos
    }

    // Método GET para obtener un método de pago por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MetodoDePago> getMetodoDePagoById(@PathVariable Long id) {
        Optional<MetodoDePago> metodoDePago = metodoDePagoRepository.findById(id);  // Busca el método de pago por ID
        return metodoDePago.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devuelve error 404
    }

    // Método POST para crear un nuevo método de pago
    @PostMapping
    public MetodoDePago createMetodoDePago(@RequestBody MetodoDePago metodoDePago) {
        return metodoDePagoRepository.save(metodoDePago);  // Guarda el nuevo método de pago en la base de datos
    }

    // Método PUT para actualizar un método de pago existente
    @PutMapping("/{id}")
    public ResponseEntity<MetodoDePago> updateMetodoDePago(@PathVariable Long id, @RequestBody MetodoDePago metodoDePagoDetails) {
        Optional<MetodoDePago> metodoDePago = metodoDePagoRepository.findById(id);  // Busca el método de pago por ID
        if (metodoDePago.isPresent()) {
            MetodoDePago updatedMetodoDePago = metodoDePago.get();
            updatedMetodoDePago.setMetodo(metodoDePagoDetails.getMetodo());  // Actualiza el nombre del método de pago
            metodoDePagoRepository.save(updatedMetodoDePago);  // Guarda los cambios
            return ResponseEntity.ok(updatedMetodoDePago);  // Devuelve el método de pago actualizado
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el método de pago, devuelve error 404
    }

    // Método DELETE para eliminar un método de pago por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoDePago(@PathVariable Long id) {
        if (metodoDePagoRepository.existsById(id)) {
            metodoDePagoRepository.deleteById(id);  // Elimina el método de pago por ID
            return ResponseEntity.noContent().build();  // Responde con código 204 (sin contenido)
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el método de pago, devuelve error 404
    }
}

