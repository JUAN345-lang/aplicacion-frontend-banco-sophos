package com.sophosBank.repository;

import com.sophosBank.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT o FROM Admin o WHERE o.email = ?1")
    Admin getUsedByEmail(String email);

    @Query("SELECT o FROM Admin o WHERE o.email = ?1 AND password = ?2")
    Admin getAdminByEmailPass(String email, String password);
}