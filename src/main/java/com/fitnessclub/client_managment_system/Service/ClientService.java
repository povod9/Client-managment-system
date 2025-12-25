package com.fitnessclub.client_managment_system.Service;

import com.fitnessclub.client_managment_system.Entity.Client;
import com.fitnessclub.client_managment_system.Entity.SubscriptionPayment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ClientService {
    Map<Long, Client> clients;
    private final AtomicLong idCounter;

    public ClientService() {
        clients = new HashMap<>();
        idCounter = new AtomicLong();
    }


    public List<Client> getAllClient() {
        return clients.values().stream().toList();
    }

    public Client getClientById(
            Long id
    ) {
        if (!clients.containsKey(id)){
            throw new NoSuchElementException("Not found client by id = " + id);
        }
        return clients.get(id);
    }


    public Client createClient(
            Client clientToCreate
    ) {
        if(clientToCreate == null){
            throw new NullPointerException("Client cannot be null");
        }


        if(clientToCreate.payment() != null){
            throw new IllegalArgumentException("For create a new client payment must be empty");
        }

        var createdClient = new Client(
                idCounter.incrementAndGet(),
                clientToCreate.firstName(),
                clientToCreate.lastName(),
                clientToCreate.email(),
                clientToCreate.subscription(),
                SubscriptionPayment.PENDING
        );

        return clients.put(createdClient.id(), createdClient);
    }

    public void deleteClientById(
            Long id
    ) {
        if (!clients.containsKey(id)){
            throw new NoSuchElementException("Not found client by id=" + id);
        }

        clients.remove(id);
    }

    public Client updateClient(
            Long id,
            Client clientToUpdate
    ) {
        if(!clients.containsKey(id)){
            throw new NoSuchElementException("Not found client by id=" + id);
        }
        var client = clients.get(id);

        if(clientToUpdate.payment() == SubscriptionPayment.PAYED){
            throw new IllegalArgumentException("Cannot modify subscription already payed!");
        }
        var updated = new Client(
                client.id(),
                clientToUpdate.firstName(),
                clientToUpdate.lastName(),
                clientToUpdate.email(),
                clientToUpdate.subscription(),
                clientToUpdate.payment()
        );
        clients.put(client.id(), updated);
        return updated;
    }
}
