package com.sophosBank.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophosBank.dto.AdminDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.Admin;
import com.sophosBank.model.Client;
import com.sophosBank.repository.IAdminRepository;
import com.sophosBank.service.IAdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {
    private final IAdminRepository adminRepository;
    private final Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public AdminServiceImpl(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public ResponseEntity<Admin> CreateAdmin(AdminDto adminDTO) throws BadRequestException {
        if (adminDTO == null){
            throw new BadRequestException("Cannot add a null admin");
        } else {
            logger.debug("Creating admin...");
            Admin admin = mapper.convertValue(adminDTO, Admin.class);
            Admin savedAdmin = adminRepository.save(admin);
            ;
            return new ResponseEntity<>(savedAdmin, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Admin> searchAdminById(Long id) throws ResourceNotFoundException {
        logger.debug("Searching admin by id: " + id);
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()){
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Admin not found by id: " + id);
        }
    }

    @Override
    public ResponseEntity<Admin>  modifyAdmin(AdminDto adminDTO) throws BadRequestException {
        if (adminDTO.getId() == null) {
            throw new BadRequestException("The admin does not exist");
        } else {
            logger.debug("Modifying admin");
            Admin admin = mapper.convertValue(adminDTO, Admin.class);
            Admin savedAdmin = adminRepository.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<Admin> removeAdmin(Long id) throws ResourceNotFoundException {
        if (adminRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Admin does not exist with id: " + id);
        } else {
            logger.debug("Remove admin by Id: " + id);
            Optional<Admin> admin = adminRepository.findById(id);
            adminRepository.delete(admin.get());
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> login(String email, String password) throws ResourceNotFoundException {
        Admin admin = adminRepository.getAdminByEmailPass(email, password);
        if(admin == null) {
            throw  new ResourceNotFoundException("Admin does not exist with that email and password");
        }
        else {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
    }


}
