package com.fitnessclub.client_managment_system.Controller;

import com.fitnessclub.client_managment_system.Entity.Client;
import com.fitnessclub.client_managment_system.Service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClient(){
        log.info("Called method getAllClient");
        var allClients = service.getAllClient();
        return ResponseEntity.status(200).body(allClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(
            @PathVariable("id") Long id
    ){
        log.info("Called method getClientById: id=" + id);
        var clientById = service.getClientById(id);
        return ResponseEntity.status(200).body(clientById);
    }

    @PostMapping()
    public ResponseEntity<Client> creatClient(
            @PathVariable("id") Long id,
            @RequestBody Client clientToCreate
    ){
        log.info("Called method createClient");
        var client = service.createClient(id, clientToCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody Client clientToUpdate
    ){
        log.info("Called method updateClient id={}, updateClient={}", id, clientToUpdate);

        var updatedClient = service.updateClient(id, clientToUpdate);
        return ResponseEntity.status(200).body(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(
            @PathVariable("id") Long id
    ){
        log.info("Called method deleteClientById id=" + id);
        service.deleteClientById(id);
        return ResponseEntity.ok()
                .build();
    }

}
