package com.sophosBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
@Configuration
@EnableTransactionManagement
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    private Long id;

    private AccountType type;
    private String accountNum;
    private AccountState state;
    private Long balance;
    private Long availableBalance;
    private boolean gmfExempt;
    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
    @LastModifiedDate
    @Column(name = "modification_date", nullable = false)
    private Date modificationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "createAdmin_id", nullable = false)
    private Admin creationUser;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "modAdmin_id", nullable = false)
    private Admin modificationUser;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "originAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> outTransactions = new HashSet<>();

    @OneToMany(mappedBy = "destinationAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> inTransactions = new HashSet<>();
    public Account(){

    }

    public Account(AccountType type, String accountNum, AccountState state, Long balance, Long availableBalance, boolean gmfExempt, Date creationDate, Date modificationDate, Admin creationUser, Admin modificationUser, Client client) {
        this.type = type;
        this.accountNum = accountNum;
        this.state = state;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.gmfExempt = gmfExempt;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.creationUser = creationUser;
        this.modificationUser = modificationUser;
        this.client = client;
    }
}
