package com.cadastro.sistema.service;


import com.cadastro.sistema.dto.ClientsDTO;
import com.cadastro.sistema.entities.Client;
import com.cadastro.sistema.repositories.ClientRepository;
import com.cadastro.sistema.service.exceptions.DatabaseException;
import com.cadastro.sistema.service.exceptions.ResorceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientsDTO insert(ClientsDTO dto) {
        log.info("Inserting client");
        Client client = new Client();
        copyDtoToEntity(dto, client);
        client = repository.save(client);
        return new ClientsDTO(client);
    }

    @Transactional(readOnly = true)
    public ClientsDTO findById(int id) {
        log.info("Localizando cliente com o id: {}", id);
        Client client = repository.findById(id).orElseThrow(
                () -> new ResorceNotFoundException("Cliente não Localizado: " + id));
        return new ClientsDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientsDTO> findAll(Pageable pageable) {
        log.info("Select em Todos os Clientes");
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientsDTO(x));


    }

    @Transactional()
    public ClientsDTO update(Long id, ClientsDTO dto) {
        log.info("Update do cliente com o id: {}", id);
        try{
            Client client = repository.getReferenceById(Math.toIntExact(id));
            copyDtoToEntity(dto, client);
            client = repository.save(client);
            return new ClientsDTO(client);

        } catch (EntityNotFoundException e) {
            throw new ResorceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        log.info("Realizando delete do id: {}", id);
        if (!repository.existsById(Math.toIntExact(id))) {
            throw new ResorceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(Math.toIntExact(id));
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }



    private void copyDtoToEntity(ClientsDTO dto, Client client) {
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate() != null ? LocalDate.parse(dto.getBirthDate()) : null);
        client.setChildren(dto.getChildren());

    }
}