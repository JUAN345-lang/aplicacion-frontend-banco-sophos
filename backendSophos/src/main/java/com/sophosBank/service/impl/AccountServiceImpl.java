package com.sophosBank.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sophosBank.dto.AccountDto;
import com.sophosBank.exceptions.BadRequestException;
import com.sophosBank.exceptions.ResourceNotFoundException;
import com.sophosBank.model.Account;
import com.sophosBank.model.AccountState;
import com.sophosBank.repository.IAccountRepository;
import com.sophosBank.service.IAccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    private final Logger logger = Logger.getLogger(AccountServiceImpl.class);

    @Autowired
    ObjectMapper mapper;

    @Autowired
    public AccountServiceImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public ResponseEntity<?> CreateAccount(AccountDto accountDto) throws BadRequestException {
        if (accountDto == null){
            throw new BadRequestException("Cannot add a null account");
        }
        else if(accountDto.getBalance() < 0) {
            throw  new BadRequestException("Cannot add an account with balance minor than zero");
        }
        else {
            Collection<Account> accounts = accountRepository.getGmfExemptsAccounts();
            if(accounts.isEmpty() || !accountDto.isGmfExempt()) {
                logger.debug("Creating account...");
                accountDto.setState(AccountState.ACTIVE);
                accountDto.setAccountNum(accountDto.generateAccountNum());
                Account account = mapper.convertValue(accountDto, Account.class);
                Account savedAccount = accountRepository.save(account);
                return new ResponseEntity<>(savedAccount, HttpStatus.OK);
            }
            else {
                throw new BadRequestException("Already exists an account with ");
            }

        }
    }

    @Override
    public ResponseEntity<?> searchAccountById(Long id) throws ResourceNotFoundException {
        logger.debug("Searching account by id: " + id);
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Account not found by id: " + id);
        }
    }

    @Override
    public ResponseEntity<?> modifyAccount(AccountDto accountDto) throws BadRequestException {
        if (accountDto.getId() == null) {
            throw new BadRequestException("The account does not exist");
        } else {
            logger.debug("Modifying account with id: " + accountDto.getId());
            Account account = mapper.convertValue(accountDto, Account.class);
            Account savedAccount = accountRepository.save(account);
            return new ResponseEntity<>(savedAccount, HttpStatus.OK );
        }
    }

    @Override
    public ResponseEntity<?> removeAccount(Long id) throws ResourceNotFoundException {
        if (accountRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Account does not exist with id: " + id);
        } else {
            logger.debug("Remove account by Id: " + id);
            Optional<Account> account = accountRepository.findById(id);
            accountRepository.delete(account.get());
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<?> searchAccountByUserId(Long id) throws ResourceNotFoundException {
        logger.debug("Searching account by user Id: " + id);
        Collection<Account> accounts = accountRepository.getAccountsByClientId(id);
        if(!accounts.isEmpty()){
            return new ResponseEntity<>(accounts.stream().toList(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Accounts not found by id: " + id);
        }
    }

    @Override
    public ResponseEntity<?> changeAccountState(Long id, AccountState state) throws BadRequestException {
        Optional<Account> account = accountRepository.findById(id);
        System.out.println(account.get());
        if(account.isEmpty()) {
            throw  new BadRequestException("Account does not exist with id: " + id);
        }
        else {
            if(state.equals(AccountState.CANCELED)) {
                Long balance = account.get().getBalance();
                if(balance < 1 && balance >= 0) {
                    accountRepository.modifyAccountStatus(state, id);
                    account.get().setState(state);
                    return new ResponseEntity<>(account.get(), HttpStatus.OK);
                }
                else {
                    throw  new BadRequestException("Account cannot be removed due to wrong balance");
                }
            }
        }

        accountRepository.modifyAccountStatus(state, id);
        account.get().setState(state);
        return new ResponseEntity<>(account.get(), HttpStatus.OK);
    }
}
