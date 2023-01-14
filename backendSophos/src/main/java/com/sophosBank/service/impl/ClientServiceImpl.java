package com.sophosBank.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophosBank.dto.ClientDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.Client;
import com.sophosBank.repository.IClientRepository;
import com.sophosBank.service.IClientService;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientServiceImpl implements IClientService {
    private final IClientRepository clientRepository;
    private final Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public ClientServiceImpl(IClientRepository clientRepository) { this.clientRepository = clientRepository;}


    @Override
    public ResponseEntity<Client> createClient(ClientDto clientDTO) throws BadRequestException {
        if (clientDTO == null){
            throw new BadRequestException("Cannot add a null client");
        } else {
            logger.debug("Creating client...");
            Client client = mapper.convertValue(clientDTO, Client.class);
            Client savedClient = clientRepository.save(client);
            return new ResponseEntity<>(savedClient, HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<Client> searchClientById(Long id) throws ResourceNotFoundException {
        logger.debug("Searching client by id: " + id);
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Client not found by id: " + id);
        }
    }

    @Override
    public ResponseEntity<Client> modifyClient(Long id,ClientDto clientDTO) throws ResourceNotFoundException {
        if (clientDTO.getId() == null) {
            throw new ResourceNotFoundException("The client does not exist");
        } else {
            logger.debug("Modifying client");
            Client client = mapper.convertValue(clientDTO, Client.class);
            Client savedClient = clientRepository.save(client);
            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        }
    }

    @Override
    public void removeClient(Long id) throws ResourceNotFoundException {
        if (clientRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Client does not exist with id: " + id);
        } else {
            logger.debug("Remove client by Id: " + id);
            clientRepository.delete(clientRepository.findById(id).get());
        }
    }

    @Override
    public ResponseEntity<List<ClientDto>> listClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            throw new ServiceException("There are not clients to list");
        } else {
            Set<ClientDto> clientDto = new HashSet<>();
            for (Client client : clients) {
                clientDto.add(mapper.convertValue(client, ClientDto.class));
            }
            logger.debug("Listing clients...");
            return new ResponseEntity<>(clientDto.stream().toList(), HttpStatus.OK) ;
        }
    }
}
