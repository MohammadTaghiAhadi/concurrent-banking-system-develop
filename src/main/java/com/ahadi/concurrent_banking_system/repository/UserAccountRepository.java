package com.ahadi.concurrent_banking_system.repository;

import com.ahadi.concurrent_banking_system.config.core.BaseRepository;
import com.ahadi.concurrent_banking_system.entity.UserAccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends BaseRepository<UserAccountEntity> {
    UserAccountEntity findFirstByAccountNumber(String accountNumber);
}
