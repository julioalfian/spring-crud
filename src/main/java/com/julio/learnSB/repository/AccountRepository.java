package com.julio.learnSB.repository;

import com.julio.learnSB.repository.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    Optional<Account> findByEmailAndActiveTrue(String email);
    Optional<Account> findByEmail(String email);
}
