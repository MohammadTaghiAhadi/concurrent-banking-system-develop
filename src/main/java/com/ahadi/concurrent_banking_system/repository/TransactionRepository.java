package com.ahadi.concurrent_banking_system.repository;

import com.ahadi.concurrent_banking_system.config.core.BaseRepository;
import com.ahadi.concurrent_banking_system.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<TransactionEntity> {

    List<TransactionEntity> findAllByUserAccountId(Long userAccountId);
}
