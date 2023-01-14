package com.sophosBank.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophosBank.dto.TransactionDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.*;
import com.sophosBank.repository.IAccountRepository;
import com.sophosBank.repository.ITransactionRepository;
import com.sophosBank.service.ITransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final ITransactionRepository transactionrepository;
    private final Logger logger = Logger.getLogger(TransactionServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public TransactionServiceImpl( ITransactionRepository transactionrepository ) {
        this.transactionrepository = transactionrepository;
    }

    @Override
    public ResponseEntity<?> createTransaction(TransactionDto transactionDto) throws BadRequestException {
        if( transactionDto ==  null) {
            throw new BadRequestException("Transaction cannot be empty");
        }
        else if(transactionDto.getOriginAccount().getState().equals(AccountState.INACTIVE)) {
            throw  new BadRequestException("Origin account cannot be inactive");
        }
        else {
            logger.debug("Creating transaction..");
            Transaction transaction = mapper.convertValue(transactionDto, Transaction.class);
            long originFinalValue = transaction.getOriginAccount().getBalance()- transaction.getOperationValue();
            long destinationFinalValue = transaction.getDestinationAccount().getBalance() + transaction.getOperationValue();

            transaction.getOriginAccount().setBalance(originFinalValue);
            transaction.getDestinationAccount().setBalance(destinationFinalValue);
            Transaction savedTransaction = transactionrepository.save(transaction);

            if(!transactionDto.getTransactionType().equals(TransactionType.RETIRO)) {
                Transaction creditTransaction = new Transaction(
                        TransactionType.GMF,
                        "Recepción en cuenta: " + transactionDto.getOperationValue(), -4000,
                        TypeMove.CREDITO,
                        transactionDto.getOriginAccount(),
                        transactionDto.getDestinationAccount(),
                        transactionDto.getOperationValue());
                transactionrepository.save(creditTransaction);
            }

            Transaction transactionGmf = new Transaction(
                    TransactionType.GMF,
                    "GMF 4 X 1000", -4000,
                    TypeMove.DEBITO,
                    transactionDto.getOriginAccount(),
                    transactionDto.getDestinationAccount(),
                    transactionDto.getOperationValue() - 4000);
            transactionGmf.getOriginAccount().setBalance(originFinalValue - 4000);
            transactionGmf.getDestinationAccount().setBalance(destinationFinalValue);
            transactionrepository.save(transactionGmf);

            return new ResponseEntity<>(transactionGmf, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> searchTransaction(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<?> listTransactions(Long id) throws ResourceNotFoundException {
        return null;
    }
}
