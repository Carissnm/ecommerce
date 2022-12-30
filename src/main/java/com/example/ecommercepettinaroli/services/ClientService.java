package com.example.ecommercepettinaroli.services;

import com.example.ecommercepettinaroli.models.Client;
import com.example.ecommercepettinaroli.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService implements Serializable {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveOrUpdate(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findClientByDni(String dni) {
        return clientRepository.findClientByDni(dni);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
