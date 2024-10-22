package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.app.entities.Estudiantes;
import com.parcial.app.repository.EstudiantesRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesRestController {

    @Autowired
    private EstudiantesRepository estudiantesRepositorio;

    // GET all estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiantes>> getAllEstudiantes() {
        List<Estudiantes> estudiantes = estudiantesRepositorio.findAll();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    // GET a single estudiante by ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> getEstudianteById(@PathVariable("id") String id) {
        return estudiantesRepositorio.findById(id)
                .map(estudiante -> new ResponseEntity<>(estudiante, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new estudiante
    @PostMapping
    public ResponseEntity<Estudiantes> createEstudiante(@RequestBody Estudiantes estudiante) {
        Estudiantes newEstudiante = estudiantesRepositorio.save(estudiante);
        return new ResponseEntity<>(newEstudiante, HttpStatus.CREATED);
    }

    // PUT to update an existing estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Estudiantes> updateEstudiante(@PathVariable("id") String id, @RequestBody Estudiantes estudiante) {
        return estudiantesRepositorio.findById(id)
                .map(existingEstudiante -> {
                    // Actualizar todos los campos necesarios
                    existingEstudiante.setTipoDocumento(estudiante.getTipoDocumento());
                    existingEstudiante.setNumeroDocumento(estudiante.getNumeroDocumento());
                    existingEstudiante.setPrimerApellido(estudiante.getPrimerApellido());
                    existingEstudiante.setSegundoApellido(estudiante.getSegundoApellido());
                    existingEstudiante.setPrimerNombre(estudiante.getPrimerNombre());
                    existingEstudiante.setSegundoNombre(estudiante.getSegundoNombre());
                    existingEstudiante.setCorreoElectronico(estudiante.getCorreoElectronico());
                    existingEstudiante.setNumeroTelefonico(estudiante.getNumeroTelefonico());
                    existingEstudiante.setNumeroRegistro(estudiante.getNumeroRegistro());
                    existingEstudiante.setPuntaje(estudiante.getPuntaje());
                    existingEstudiante.setMediaNacional(estudiante.getMediaNacional());
                    existingEstudiante.setAprobadoUTS(estudiante.isAprobadoUTS());
                    existingEstudiante.setComunicacionEscrita(estudiante.getComunicacionEscrita());
                    existingEstudiante.setRazonamientoCuantitativo(estudiante.getRazonamientoCuantitativo());
                    existingEstudiante.setLecturaCritica(estudiante.getLecturaCritica());
                    existingEstudiante.setCompetenciasCiudadanas(estudiante.getCompetenciasCiudadanas());
                    existingEstudiante.setIngles(estudiante.getIngles());
                    existingEstudiante.setFormulacionProyectosIngenieria(estudiante.getFormulacionProyectosIngenieria());
                    existingEstudiante.setPensamientoCientifico(estudiante.getPensamientoCientifico());
                    existingEstudiante.setDisenoSoftware(estudiante.getDisenoSoftware());
                    
                    Estudiantes updatedEstudiante = estudiantesRepositorio.save(existingEstudiante);
                    return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE an estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEstudiante(@PathVariable("id") String id) {
        try {
            estudiantesRepositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET estudiantes by puntaje (for custom search functionality)
    @GetMapping("/puntaje/{puntaje}")
    public ResponseEntity<List<Estudiantes>> getEstudiantesByPuntaje(@PathVariable("puntaje") int puntaje) {
        // Implement custom search logic here if needed
        List<Estudiantes> estudiantes = estudiantesRepositorio.findAll(); // Implementar filtro aquí
        // Filtrar estudiantes según el puntaje
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    // POST login for estudiante
 // POST login for estudiante
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Estudiantes credentials) {
        Optional<Estudiantes> estudiante = estudiantesRepositorio.findByNumeroDocumento(credentials.getNumeroDocumento()); // Cambiado a numeroDocumento

        if (estudiante.isPresent() && 
            estudiante.get().getContrasena().equals(credentials.getContrasena())) {
            // Login exitoso
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login exitoso");
            response.put("estudiante", estudiante.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
    }

    // GET verificar if estudiante exists
    @GetMapping("/verificar/{numeroDocumento}")
    public ResponseEntity<Boolean> verificarEstudiante(@PathVariable String numeroDocumento) {
        Optional<Estudiantes> estudiante = estudiantesRepositorio.findByNumeroDocumento(numeroDocumento); // Cambiado a numeroDocumento
        return new ResponseEntity<>(estudiante.isPresent(), HttpStatus.OK);
    }

}
