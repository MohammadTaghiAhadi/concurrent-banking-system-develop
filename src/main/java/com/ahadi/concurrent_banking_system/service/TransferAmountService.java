package com.ahadi.concurrent_banking_system.service;

import com.ahadi.concurrent_banking_system.config.core.BaseService;
import com.ahadi.concurrent_banking_system.entity.TransferAmountEntity;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import org.springframework.stereotype.Service;

@Service
public interface TransferAmountService extends BaseService<TransferAmountModel, TransferAmountEntity>  {
}
