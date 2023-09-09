package com.example.demo.repository;

import com.example.demo.entitys.perfilEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface PerfilRepository extends MongoRepository<perfilEntity, String> {
    perfilEntity findByNombre(String nombre);
}
