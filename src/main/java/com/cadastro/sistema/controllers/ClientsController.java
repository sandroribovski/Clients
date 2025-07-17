package com.cadastro.sistema.controllers;


import com.cadastro.sistema.dto.ClientsDTO;
import com.cadastro.sistema.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

    @Autowired
    private ClientService service;


    @PostMapping()
    public ResponseEntity<ClientsDTO> insert(@Valid @RequestBody ClientsDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientsDTO> findById(@PathVariable Long id) {
        ClientsDTO dto = service.findById(Math.toIntExact(id));

        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<Page<ClientsDTO>> findAll(Pageable pageable) {
        Page<ClientsDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<ClientsDTO> update(@PathVariable Long id, @Valid @RequestBody ClientsDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}
