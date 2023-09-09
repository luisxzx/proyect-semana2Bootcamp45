    package com.example.demo;

    import com.example.demo.entitys.ClientEntity;
    import com.example.demo.entitys.TipoClienteEntity;
    import com.example.demo.entitys.perfilEntity;
    import com.example.demo.mapper.ClientMapper;
    import com.example.demo.model.Client;
    import com.example.demo.repository.ClientRepository;
    import com.example.demo.repository.PerfilRepository;
    import com.example.demo.repository.TipoClienteRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.bson.types.ObjectId;
    import java.util.List;
    import java.util.Optional;
    import java.util.function.Predicate;
    import java.util.stream.Collectors;

    @Service
    public class ClientService {

        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private TipoClienteRepository tipoClienteRepository;
        @Autowired
        private PerfilRepository perfilRepository;

        public Client createClient(Client clientDto) {
            TipoClienteEntity tipoClienteEntity = tipoClienteRepository.findByNombre(clientDto.getTipoCliente().getNombre());
            if (tipoClienteEntity == null) {
                tipoClienteEntity = createOrGetTipoClienteEntity(clientDto.getTipoCliente().getNombre());
            }
            clientDto.setTipoCliente(ClientMapper.entityTypeToDtoType(tipoClienteEntity));

            ClientEntity entityToSave = ClientMapper.dtoToEntity(clientDto);
            ClientEntity savedEntity = clientRepository.save(entityToSave);
            return ClientMapper.entityToDto(savedEntity);
        }

        private TipoClienteEntity createOrGetTipoClienteEntity(String nombreTipoCliente) {
            Predicate<String> isPersonal = "personal"::equalsIgnoreCase;
            Predicate<String> isEmpresarial = "empresarial"::equalsIgnoreCase;

            if (!isPersonal.or(isEmpresarial).test(nombreTipoCliente)) {
                throw new IllegalArgumentException("El valor de nombreTipoCliente no es válido.");
            }
            String nombrePerfil = isEmpresarial.test(nombreTipoCliente) ? "pyme" : "vip";

            perfilEntity existingPerfilEntity = perfilRepository.findByNombre(nombrePerfil);
            if (existingPerfilEntity == null) {
                existingPerfilEntity = createPerfilEntity(nombrePerfil);
            }

            TipoClienteEntity tipoClienteEntity = TipoClienteEntity.builder()
                    .nombre(nombreTipoCliente)
                    .perfil(existingPerfilEntity)
                    .build();

            return tipoClienteRepository.save(tipoClienteEntity);
        }

        private perfilEntity createPerfilEntity(String nombrePerfil) {
            perfilEntity newPerfilEntity = perfilEntity.builder().nombre(nombrePerfil).build();
            return perfilRepository.save(newPerfilEntity);
        }

        public List<Client> getAllClients() {
            List<ClientEntity> entities = clientRepository.findAll();
            return entities.stream().map(ClientMapper::entityToDto).collect(Collectors.toList());
        }

        public Client getClientById(String id) {
            if (!ObjectId.isValid(id)) {
                return null;
            }

            return clientRepository.findById(id)
                    .map(ClientMapper::entityToDto)
                    .orElse(null);
        }

        public List<Client> bulkRetrieveClients(List<String> ids) {
            List<ObjectId> objectIds = ids.stream().map(ObjectId::new).collect(Collectors.toList());
            List<ClientEntity> entities = clientRepository.findAllByGivenIds(objectIds);
            return entities.stream().map(ClientMapper::entityToDto).collect(Collectors.toList());
        }

    }