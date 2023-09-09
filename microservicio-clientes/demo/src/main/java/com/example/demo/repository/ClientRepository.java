package com.example.demo.repository;

import com.example.demo.entitys.ClientEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import java.util.List;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
    @Query("{ '_id': { $in: ?0 } }")
    List<ClientEntity> findAllByGivenIds(List<ObjectId> ids);
}
