package com.secured.banking.feature.account.repository;

import com.secured.banking.entities.Account;
import com.secured.banking.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
    @Query("select a from Address a where a.Account.Id = ?1")
    Address findByAccountId(Integer id);
}
