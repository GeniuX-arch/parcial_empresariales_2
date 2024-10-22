package com.parcial.app.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.parcial.app.entities.Estudiantes;

public interface EstudiantesRepository extends MongoRepository<Estudiantes, String> {
	Optional<Estudiantes> findByNumeroDocumento(String numeroDocumento);
}