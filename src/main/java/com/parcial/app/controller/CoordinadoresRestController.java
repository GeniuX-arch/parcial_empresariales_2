package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.app.entities.Coordinadores;
import com.parcial.app.repository.CoordinadoresRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/coordinadores")
public class CoordinadoresRestController {

    @Autowired
    private CoordinadoresRepository coordinadoresRepository;

    // GET all coordinadores
    @GetMapping
    public ResponseEntity<List<Coordinadores>> getAllCoordinadores() {
        List<Coordinadores> coordinadores = coordinadoresRepository.findAll();
        return new ResponseEntity<>(coordinadores, HttpStatus.OK);
    }

    // GET a single coordinador by ID
    @GetMapping("/{id}")
    public ResponseEntity<Coordinadores> getCoordinadorById(@PathVariable("id") String id) {
        return coordinadoresRepository.findById(id)
                .map(coordinador -> new ResponseEntity<>(coordinador, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new coordinador
    @PostMapping
    public ResponseEntity<Coordinadores> createCoordinador(@RequestBody Coordinadores coordinador) {
        Coordinadores newCoordinador = coordinadoresRepository.save(coordinador);
        return new ResponseEntity<>(newCoordinador, HttpStatus.CREATED);
    }

    // PUT to update an existing coordinador
    @PutMapping("/{id}")
    public ResponseEntity<Coordinadores> updateCoordinador(@PathVariable("id") String id, @RequestBody Coordinadores coordinador) {
        return coordinadoresRepository.findById(id)
                .map(existingCoordinador -> {
                    existingCoordinador.setCedula(coordinador.getCedula());
                    existingCoordinador.setContrasena(coordinador.getContrasena());
                    // Añade aquí otros campos que necesiten ser actualizados
                    Coordinadores updatedCoordinador = coordinadoresRepository.save(existingCoordinador);
                    return new ResponseEntity<>(updatedCoordinador, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE a coordinador
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCoordinador(@PathVariable("id") String id) {
        try {
            coordinadoresRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Coordinadores credentials) {
        Optional<Coordinadores> coordinador = coordinadoresRepository
            .findByCedula(credentials.getCedula());
        
        if (coordinador.isPresent() && 
            coordinador.get().getContrasena().equals(credentials.getContrasena())) {
            // Login exitoso
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("coordinador", coordinador.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/verificar/{cedula}")
    public ResponseEntity<Boolean> verificarCoordinador(@PathVariable String cedula) {
        Optional<Coordinadores> coordinador = coordinadoresRepository.findByCedula(cedula);
        return new ResponseEntity<>(coordinador.isPresent(), HttpStatus.OK);
    }
}