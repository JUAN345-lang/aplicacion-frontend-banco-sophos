package com.sophosBank.repository;

import com.sophosBank.model.Account;
import com.sophosBank.model.AccountState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT o FROM Account o WHERE o.client.id = ?1")
    Collection<Account>  getAccountsByClientId(Long id);

    @Query("SELECT o FROM Account o WHERE o.gmfExempt = true")
    Collection<Account> getGmfExemptsAccounts();

    @Modifying(clearAutomatically = true)
    @Query("update Account u set u.state = ?1 WHERE u.id = ?2")
    @Transactional
    int modifyAccountStatus( AccountState state, Long Id);
}
