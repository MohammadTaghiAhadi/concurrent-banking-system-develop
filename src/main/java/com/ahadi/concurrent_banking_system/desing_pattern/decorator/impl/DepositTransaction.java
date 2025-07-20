package com.ahadi.concurrent_banking_system.desing_pattern.decorator.impl;

import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.desing_pattern.decorator.base.TransactionDecorator;
import com.ahadi.concurrent_banking_system.enums.TransactionType;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.desing_pattern.observer.TransactionLogger;
import com.ahadi.concurrent_banking_system.service.TransactionService;
import com.ahadi.concurrent_banking_system.service.UserAccountService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DepositTransaction extends TransactionDecorator {

    final TransactionService transactionService;
    final UserAccountService userAccountService;

    public DepositTransaction(TransactionService transactionService, UserAccountService userAccountService) {
        this.transactionService = transactionService;
        this.userAccountService = userAccountService;
        events.subscribe(TransactionType.DEPOSIT.getTitle(), new TransactionLogger());
    }

    @Override
    @Transactional(isolation= Isolation.SERIALIZABLE)//lock when read and write
    public TransactionModel doTransaction(TransactionModel model) throws BusinessException {
        //find user
        //create new transaction
        //update user account balance
        //notify to transaction logger
        checkValidation(model);
        UserAccountModel userAccountModel = userAccountService.findById(model.getUserAccountId());
        if (userAccountModel == null)
            throw new BusinessException("user.not.found.with.id");
        TransactionModel transactionModel = transactionService.create(model);
        userAccountModel.setBalance(userAccountModel.getBalance() + model.getAmount());
        userAccountService.update(userAccountModel);
        events.notify(transactionModel.getUserAccountId(), TransactionType.DEPOSIT.getTitle(), transactionModel.getAmount());
        return transactionModel;
    }

    @Override
    public void checkValidation(TransactionModel model) throws BusinessException {
        if (model == null)
            throw new BusinessException("data.is.null");
        if (model.getAmount() == null)
            throw new BusinessException("amount.must.be.not.null");
        if (model.getUserAccountId() == null)
            throw new BusinessException("user.account.id.is.null");
    }

}
