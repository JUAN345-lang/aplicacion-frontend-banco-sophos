package com.sophosBank.controller;

import com.sophosBank.dto.AccountDto;
import com.sophosBank.dto.ClientDto;

import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.AccountState;
import com.sophosBank.model.Client;
import com.sophosBank.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private IClientService clientService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) throws BadRequestException {
        return clientService.createClient(clientDto);

    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> listClients() throws ResourceNotFoundException {
        return this.clientService.listClients();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) throws ResourceNotFoundException {
        return this.clientService.searchClientById(id);
    }

    @CrossOrigin
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) throws ResourceNotFoundException {
        return this.clientService.modifyClient(id, clientDto);
    }
}
