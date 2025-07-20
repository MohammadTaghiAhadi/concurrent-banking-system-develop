package com.ahadi.concurrent_banking_system.service;

import com.ahadi.concurrent_banking_system.config.core.BaseService;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.entity.TransactionEntity;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService extends BaseService<TransactionModel, TransactionEntity>  {

    List<TransactionModel> findAllByUserAccountId(Long userAccountId) throws BusinessException;
}
