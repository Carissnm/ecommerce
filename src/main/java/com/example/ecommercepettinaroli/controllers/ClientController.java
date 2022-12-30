package com.example.ecommercepettinaroli.controllers;

import com.example.ecommercepettinaroli.models.Client;
import com.example.ecommercepettinaroli.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/listar")
    public ResponseEntity<?> getAllClients() {
        try {
            List<Client> clients = clientService.findAllClients();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getClientById(@PathVariable(name = "id") Long id) {
        try {
            Optional<Client> client = clientService.findClientById(id);
            if(client.isPresent()) {
                return ResponseEntity.ok(client);
            } else {
                return new ResponseEntity<>("No se encontró ningún cliente de id " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Optional<Client> checkClient = clientService.findClientByDni(client.getDni());
            if(checkClient.isPresent()) {
                return new ResponseEntity<>("El cliente ya se encuentra registrado", HttpStatus.CONFLICT);
            } else {
                Client savedClient = clientService.saveOrUpdate(client);
                return ResponseEntity.ok(savedClient);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }
}
