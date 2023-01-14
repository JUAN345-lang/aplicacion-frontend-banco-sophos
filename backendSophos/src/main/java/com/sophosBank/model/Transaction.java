package com.sophosBank.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Transactions")

@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private Long id;

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
    private TransactionType transactionType;
    private String description;
    @Column(name="operation_Value")
    private long operationValue;
    private TypeMove typeMove;

    private long finalBalance;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "originAccount_id", nullable = false)
    private Account originAccount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "destinationAccount_id", nullable = false)
    private Account destinationAccount;


    public  Transaction(){}

    public Transaction(TransactionType transactionType, String description, long operationValue, TypeMove typeMove ,Account originAccount, Account destinationAccount, long finalBalance) {
        this.transactionType = transactionType;
        this.description = description;
        this.operationValue = operationValue;
        this.typeMove = typeMove;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.finalBalance = finalBalance;
    }

    public Transaction(Date creationDate, TransactionType transactionType, String description, long operationValue, TypeMove typeMove ,Account originAccount, Account destinationAccount, long finalBalance) {
        this.creationDate = creationDate;
        this.transactionType = transactionType;
        this.description = description;
        this.operationValue = operationValue;
       this.typeMove = typeMove;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.finalBalance = finalBalance;
    }
}
