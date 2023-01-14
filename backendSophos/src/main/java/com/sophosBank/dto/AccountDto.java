package com.sophosBank.dto;

import com.sophosBank.model.AccountState;
import com.sophosBank.model.AccountType;
import com.sophosBank.model.Admin;
import com.sophosBank.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AccountDto {
    private Long id;

    private AccountType type;
    private String accountNum;
    private AccountState state;
    private Long balance;
    private Long availableBalance;
    private boolean gmfExempt;
    private Date creationDate;
    private Date modificationDate;
    private Client client;
    private Admin modificationUser;
    private Admin creationUser;

    public Admin getModificationUser() {
        return modificationUser;
    }

    public void setModificationUser(Admin modificationUser) {
        this.modificationUser = modificationUser;
    }

    public Admin getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(Admin creationUser) {
        this.creationUser = creationUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public AccountState getState() {
        return state;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }

    public boolean isGmfExempt() {
        return gmfExempt;
    }

    public void setGmfExempt(boolean gmfExempt) {
        this.gmfExempt = gmfExempt;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AccountDto(){

    }

    public AccountDto(AccountType type, AccountState state, Long balance, Long availableBalance, boolean gmfExempt, Client client, Admin creationUser, Admin modificationUser) {
        this.type = type;
        this.state = state;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.gmfExempt = gmfExempt;
        this.client = client;
        this.creationUser = creationUser;
        this.modificationUser = modificationUser;
    }

    public String generateAccountNum() {
        String accountNum = "";
        accountNum = this.type.equals(AccountType.AHORROS) ? "46" : "23";
        for(int i = 0; i < 8; i++) {
            accountNum += (int) (Math.random()*10);
        }
        return accountNum;
    }
}
