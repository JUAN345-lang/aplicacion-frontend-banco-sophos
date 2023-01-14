package com.sophosBank.controller;


import com.sophosBank.dto.AccountDto;
import com.sophosBank.dto.TransactionDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.service.IAccountService;
import com.sophosBank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) throws BadRequestException {
        return transactionService.createTransaction(transactionDto);
    }

}
