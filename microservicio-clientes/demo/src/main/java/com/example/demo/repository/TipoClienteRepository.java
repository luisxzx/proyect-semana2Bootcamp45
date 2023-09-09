package com.example.demo.repository;

import com.example.demo.entitys.TipoClienteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface TipoClienteRepository extends MongoRepository<TipoClienteEntity, String> {
    TipoClienteEntity findByNombre(String nombre);
}