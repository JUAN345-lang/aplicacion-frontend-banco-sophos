package com.sophosBank.service;

import com.sophosBank.dto.AdminDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface IAdminService {
    ResponseEntity<?> CreateAdmin(AdminDto adminDTO) throws BadRequestException;
    ResponseEntity<?> searchAdminById(Long id) throws ResourceNotFoundException;
    ResponseEntity<?> modifyAdmin(AdminDto adminDTO) throws BadRequestException;
    ResponseEntity<?> removeAdmin(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> login(String email, String password) throws ResourceNotFoundException;
}
