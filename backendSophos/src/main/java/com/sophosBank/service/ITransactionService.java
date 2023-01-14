package com.sophosBank.service;

import com.sophosBank.dto.AdminDto;
import com.sophosBank.dto.TransactionDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface ITransactionService {
    ResponseEntity<?> createTransaction(TransactionDto transactionDto) throws BadRequestException;
    ResponseEntity<?> searchTransaction(Long id) throws ResourceNotFoundException;
    ResponseEntity<?> listTransactions(Long id) throws ResourceNotFoundException;
}
