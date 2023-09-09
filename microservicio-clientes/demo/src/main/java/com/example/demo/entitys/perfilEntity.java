package com.example.demo.entitys;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "profiles")
public class perfilEntity {
    @Id
    private String id;
    private String nombre;

}