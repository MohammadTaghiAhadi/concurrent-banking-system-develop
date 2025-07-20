package com.ahadi.concurrent_banking_system.service.impl;

import com.ahadi.concurrent_banking_system.config.core.BaseServiceImpl;
import com.ahadi.concurrent_banking_system.entity.TransferAmountEntity;
import com.ahadi.concurrent_banking_system.mapper.TransferAmountMapper;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import com.ahadi.concurrent_banking_system.repository.TransferAmountRepository;
import com.ahadi.concurrent_banking_system.service.TransferAmountService;
import com.ahadi.concurrent_banking_system.validator.TransferAmountValidator;
import org.springframework.stereotype.Service;

@Service
public class TransferAmountServiceImpl extends BaseServiceImpl<TransferAmountModel, TransferAmountEntity> implements TransferAmountService {

    final TransferAmountValidator transferAmountValidator;
    final TransferAmountMapper transferAmountMapper;
    final TransferAmountRepository transferAmountRepository;


    public TransferAmountServiceImpl(TransferAmountValidator transferAmountValidator, TransferAmountMapper transferAmountMapper, TransferAmountRepository transferAmountRepository) {
        super(transferAmountValidator,transferAmountMapper, transferAmountRepository);
        this.transferAmountValidator = transferAmountValidator;
        this.transferAmountMapper = transferAmountMapper;
        this.transferAmountRepository = transferAmountRepository;
    }
}
