package com.sophosBank.dto;

import com.sophosBank.model.Account;
import com.sophosBank.model.TransactionType;
import com.sophosBank.model.TypeMove;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class TransactionDto {

    private Long id;
    private Date creationDate;
    private TransactionType transactionType;
    private String description;
    private long finalBalance;
    private long operationValue;
    private TypeMove typeMove;
    private Account originAccount;
    private Account destinationAccount;

    public TransactionDto() {
    }

    public TransactionDto(Date creationDate, TransactionType transactionType, String description, long finalBalance, TypeMove typeMove, Account originAccount, Account destinationAccount) {
        this.creationDate = creationDate;
        this.transactionType = transactionType;
        this.description = description;
        this.finalBalance = finalBalance;
        this.typeMove = typeMove;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public TransactionDto(TransactionType transactionType, String description, long finalBalance, TypeMove typeMove, Account originAccount, Account destinationAccount) {
        this.transactionType = transactionType;
        this.description = description;
        this.finalBalance = finalBalance;
        this.typeMove = typeMove;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeMove getTypeMove() {
        return typeMove;
    }

    public void setTypeMove(TypeMove typeMove) {
        this.typeMove = typeMove;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public long getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(long finalBalance) {
        this.finalBalance = finalBalance;
    }

    public long getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(long operationValue) {
        this.operationValue = operationValue;
    }
}
