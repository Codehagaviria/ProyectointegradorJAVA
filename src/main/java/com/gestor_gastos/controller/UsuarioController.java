package com.gestor_gastos.controller;

import com.gestor_gastos.entity.Usuario;
import com.gestor_gastos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")  // Define la ruta base para este controlador
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Repositorio para interactuar con la base de datos

    // Método GET para obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();  // Retorna todos los usuarios de la base de datos
    }

    // Método GET para obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);  // Busca el usuario por ID
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devuelve error 404
    }

    // Método POST para crear un nuevo usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);  // Guarda el nuevo usuario en la base de datos
    }

    // Método PUT para actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);  // Busca el usuario por ID
        if (usuario.isPresent()) {
            Usuario updatedUsuario = usuario.get();
            updatedUsuario.setNombre(usuarioDetails.getNombre());
            updatedUsuario.setEdad(usuarioDetails.getEdad());
            updatedUsuario.setTelefono(usuarioDetails.getTelefono());
            updatedUsuario.setCorreo(usuarioDetails.getCorreo());
            updatedUsuario.setContrasena(usuarioDetails.getContrasena());
            updatedUsuario.setFecha(usuarioDetails.getFecha());
            updatedUsuario.setCiudad(usuarioDetails.getCiudad());
            usuarioRepository.save(updatedUsuario);  // Guarda los cambios
            return ResponseEntity.ok(updatedUsuario);  // Devuelve el usuario actualizado
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el usuario, devuelve error 404
    }

    // Método DELETE para eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);  // Elimina el usuario por ID
            return ResponseEntity.noContent().build();  // Responde con código 204 (sin contenido)
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra el usuario, devuelve error 404
    }
}

