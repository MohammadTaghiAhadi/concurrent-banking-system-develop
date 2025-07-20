package com.ahadi.concurrent_banking_system.desing_pattern.decorator.base;

import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import org.springframework.stereotype.Service;

@Service
public interface Transaction {

    TransactionModel doTransaction(TransactionModel model) throws BusinessException;

    void checkValidation(TransactionModel model) throws BusinessException;
}
