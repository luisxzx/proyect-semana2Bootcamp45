package com.example.demo.mapper;

import com.example.demo.entitys.ClientEntity;
import com.example.demo.entitys.TipoClienteEntity;
import com.example.demo.entitys.perfilEntity;
import com.example.demo.model.Client;
import com.example.demo.model.Perfil;
import com.example.demo.model.TipoCliente;

public class ClientMapper {


    public static ClientEntity dtoToEntity(Client clientDto) {
        return ClientEntity.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .document(clientDto.getDocumento())
                .type(dtoTypeToEntityType(clientDto.getTipoCliente()))
                .build();
    }

    private static TipoClienteEntity dtoTypeToEntityType(TipoCliente typeDto) {
        if (typeDto == null) return null;
        return TipoClienteEntity.builder()
                .id(typeDto.getId())
                .nombre(typeDto.getNombre())
                .perfil(dtoPerfilToEntityPerfil(typeDto.getPerfil()))
                .build();
    }

    private static perfilEntity dtoPerfilToEntityPerfil(Perfil perfilDto) {
        if (perfilDto == null) return null;
        return perfilEntity.builder()
                .id(perfilDto.getId())
                .nombre(perfilDto.getNombre())
                .build();
    }


    public static Client entityToDto(ClientEntity entity) {
        return new Client()
                .id(entity.getId())
                .name(entity.getName())
                .documento(entity.getDocument())
                .tipoCliente(entityTypeToDtoType(entity.getType()));
    }

    public static TipoCliente entityTypeToDtoType(TipoClienteEntity entityType) {
        if (entityType == null) return null;
        return new TipoCliente()
                .id(entityType.getId())
                .nombre(entityType.getNombre())
                .perfil(entityPerfilToDtoPerfil(entityType.getPerfil()));
    }

    private static Perfil entityPerfilToDtoPerfil(perfilEntity perfilEntity) {
        if (perfilEntity == null) return null;
        return new Perfil()
                .id(perfilEntity.getId())
                .nombre(perfilEntity.getNombre());
    }
}

