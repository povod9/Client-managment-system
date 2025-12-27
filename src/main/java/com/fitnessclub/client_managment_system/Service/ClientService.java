package com.fitnessclub.client_managment_system.Service;

import com.fitnessclub.client_managment_system.Entity.Client;
import com.fitnessclub.client_managment_system.Entity.ClientEntity;
import com.fitnessclub.client_managment_system.Entity.ClientRepository;
import com.fitnessclub.client_managment_system.Entity.enums.SubscriptionPayment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }


    public List<Client> getAllClient() {
        List<ClientEntity> allEntities = repository.findAll();

        return allEntities.stream()
                .map(this::toDomainClient)
                .toList();
    }

    public Client getClientById(
            Long id
    ){
        ClientEntity clientEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found client by id=" + id));

        return toDomainClient(clientEntity);
    }


    public Client createClient(
            Client clientToCreate
    ) {
        if(clientToCreate == null){
            throw new NullPointerException("Client cannot be null");
        }

        var allEntity = repository.findAll();
        boolean isEmailExist = allEntity.stream().anyMatch(entity -> entity.getEmail().equals(clientToCreate.email()));

        if(isEmailExist){
            throw new IllegalArgumentException("Email already exist in system");
        }

        if(clientToCreate.payment() != null){
            throw new IllegalArgumentException("For create a new client payment must be empty");
        }

        var createdEntity = new ClientEntity(
                null,
                clientToCreate.firstName(),
                clientToCreate.lastName(),
                clientToCreate.email(),
                clientToCreate.subscription(),
                SubscriptionPayment.PENDING
        );
        var savedEntity = repository.save(createdEntity);
        return toDomainClient(savedEntity);
    }

    public Client updateClient(
            Long id,
            Client clientToUpdate
    ) {
        var clientEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found client by id=" + id));

        if(clientEntity.getPayment() != SubscriptionPayment.PAYED){
            throw new IllegalArgumentException("Cannot modify subscription already payed: " + clientEntity.getPayment());
        }
        var updated = new ClientEntity(
                clientEntity.getId(),
                clientToUpdate.firstName(),
                clientToUpdate.lastName(),
                clientToUpdate.email(),
                clientToUpdate.subscription(),
                clientToUpdate.payment()
        );

        var saved = repository.save(updated);
        return toDomainClient(saved);
    }

    public void deleteClientById(
            Long id
    ) {
        var clientEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found client by id=" + id));

        repository.delete(clientEntity);
    }

    public Client approvePaymentById(
            Long id
    ) {
        var clientEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found client by id=" + id));

        clientEntity.setPayment(SubscriptionPayment.PAYED);
        repository.save(clientEntity);
        return toDomainClient(clientEntity);
    }

    private Client toDomainClient (
            ClientEntity client
    ){
        return  new Client( client.getId(),
            client.getFirstName(),
            client.getLastName(),
            client.getEmail(),
            client.getSubscription(),
            client.getPayment()
        );
    }
}
