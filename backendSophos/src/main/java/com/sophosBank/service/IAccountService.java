package com.sophosBank.service;

import com.sophosBank.dto.AccountDto;
import com.sophosBank.dto.AdminDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.AccountState;
import org.springframework.http.ResponseEntity;

public interface IAccountService {
    ResponseEntity<?> CreateAccount(AccountDto accountDto) throws BadRequestException;
    ResponseEntity<?> searchAccountById(Long id) throws ResourceNotFoundException;
    ResponseEntity<?> modifyAccount(AccountDto accountDto) throws BadRequestException;
    ResponseEntity<?> removeAccount(Long id) throws ResourceNotFoundException;

    ResponseEntity<?> searchAccountByUserId(Long id) throws ResourceNotFoundException;
    ResponseEntity<?>  changeAccountState(Long id, AccountState state) throws BadRequestException;
}
