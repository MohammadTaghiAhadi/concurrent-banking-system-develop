package com.ahadi.concurrent_banking_system.contoller;

import com.ahadi.concurrent_banking_system.config.core.BaseController;
import com.ahadi.concurrent_banking_system.entity.TransactionEntity;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController<TransactionModel, TransactionEntity> {


    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        super(transactionService);
        this.transactionService = transactionService;
    }
}
