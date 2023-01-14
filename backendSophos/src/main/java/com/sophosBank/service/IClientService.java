package com.sophosBank.service;
import com.sophosBank.dto.ClientDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IClientService {
    ResponseEntity<Client> createClient(ClientDto clientDTO) throws BadRequestException;
    ResponseEntity<Client> searchClientById(Long id) throws ResourceNotFoundException;
    ResponseEntity<Client> modifyClient(Long id, ClientDto clientDTO) throws ResourceNotFoundException;
    void removeClient(Long id) throws ResourceNotFoundException;
    ResponseEntity<List<ClientDto>> listClients();
}
