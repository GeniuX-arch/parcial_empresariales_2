package com.parcial.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.parcial.app.entities.Coordinadores;

public interface CoordinadoresRepository extends MongoRepository<Coordinadores, String> {
	Optional<Coordinadores> findByCedula(String cedula);
}
