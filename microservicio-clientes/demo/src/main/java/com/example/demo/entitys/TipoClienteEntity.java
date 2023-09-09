package com.example.demo.entitys;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@Document(collection = "typeClients")
public class TipoClienteEntity {
    @Id
    private String id;
    private String nombre;

    @DBRef
    private perfilEntity perfil;

    // Getters, setters, etc.
}
