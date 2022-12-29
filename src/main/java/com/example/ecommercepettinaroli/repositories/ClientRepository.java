package com.example.ecommercepettinaroli.repositories;

import com.example.ecommercepettinaroli.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
