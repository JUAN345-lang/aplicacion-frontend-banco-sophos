package com.sophosBank.repository;

import com.sophosBank.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IClientRepository  extends JpaRepository<Client, Long>  { }
