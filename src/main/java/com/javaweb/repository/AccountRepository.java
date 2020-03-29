package com.javaweb.repository;

import com.javaweb.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    AccountEntity findByUsername(String username);
}
