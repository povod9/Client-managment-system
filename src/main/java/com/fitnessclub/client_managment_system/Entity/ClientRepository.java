package com.fitnessclub.client_managment_system.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
