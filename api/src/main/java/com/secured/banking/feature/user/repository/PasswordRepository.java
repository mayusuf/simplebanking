package com.secured.banking.feature.user.repository;

import com.secured.banking.feature.user.dao.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

}
