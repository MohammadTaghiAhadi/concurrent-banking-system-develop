package com.ahadi.concurrent_banking_system.service.impl;

import com.ahadi.concurrent_banking_system.config.core.BaseServiceImpl;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.entity.TransactionEntity;
import com.ahadi.concurrent_banking_system.mapper.TransactionMapper;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.repository.TransactionRepository;
import com.ahadi.concurrent_banking_system.service.TransactionService;
import com.ahadi.concurrent_banking_system.validator.TransactionValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<TransactionModel, TransactionEntity> implements TransactionService {

    final TransactionRepository transactionRepository;
    final TransactionMapper transactionMapper;
    final TransactionValidator transactionValidator;

    public TransactionServiceImpl(TransactionValidator transactionValidator,TransactionMapper transactionMapper,
                                  TransactionRepository transactionRepository) {
        super(transactionValidator, transactionMapper, transactionRepository);
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.transactionValidator = transactionValidator;
    }

    @Override
    @Transactional
    public List<TransactionModel> findAllByUserAccountId(Long userAccountId) throws BusinessException {
        transactionValidator.findAllByUserAccountIdValidate(userAccountId);
        return transactionMapper.convertToModels(transactionRepository.findAllByUserAccountId(userAccountId));
    }
}
