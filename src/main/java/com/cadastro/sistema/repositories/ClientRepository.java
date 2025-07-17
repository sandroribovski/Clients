package com.cadastro.sistema.repositories;


import com.cadastro.sistema.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    }

