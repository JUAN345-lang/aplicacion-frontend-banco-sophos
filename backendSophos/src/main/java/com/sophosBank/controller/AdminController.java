package com.sophosBank.controller;

import com.sophosBank.dto.AdminDto;
import com.sophosBank.dto.ClientDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.service.IAdminService;
import com.sophosBank.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrators")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody AdminDto adminDto) throws BadRequestException {
        return adminService.CreateAdmin(adminDto);

    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDto adminDto) throws ResourceNotFoundException {
        return  adminService.login(adminDto.getEmail(), adminDto.getPassword());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) throws  ResourceNotFoundException {
        return adminService.searchAdminById(id);
    }
}
