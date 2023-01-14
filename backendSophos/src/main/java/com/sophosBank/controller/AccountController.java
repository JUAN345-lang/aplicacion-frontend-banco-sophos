package com.sophosBank.controller;

import com.sophosBank.dto.AccountDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.AccountState;
import com.sophosBank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")

public class AccountController {

    @Autowired
    private IAccountService accountService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) throws BadRequestException {
        return accountService.CreateAccount(accountDto);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> changeAccountState(@PathVariable Long id, @Param("state") AccountState state) throws BadRequestException {
        return accountService.changeAccountState(id, state);
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> listAccountsByUserId(@PathVariable Long id) throws ResourceNotFoundException {
        return accountService.searchAccountByUserId(id);
    }
}
