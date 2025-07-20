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
public class WithdrawalTransaction extends TransactionDecorator {

    final TransactionService transactionService;
    final UserAccountService userAccountService;

    public WithdrawalTransaction(TransactionService transactionService, UserAccountService userAccountService) {
        this.transactionService = transactionService;
        this.userAccountService = userAccountService;
        events.subscribe(TransactionType.WITHDRAWAL.getTitle(), new TransactionLogger());
    }

    @Override
    @Transactional(isolation= Isolation.SERIALIZABLE)//lock when read and write
    public TransactionModel doTransaction(TransactionModel model) throws BusinessException {
        //find user
        //check user account balance
        //if user balance is greater transaction amount == transaction success
        //if user balance is less than transaction amount == transaction failed
        //if transaction success ==> create new transaction,update user account balance
        //if transaction failed ==> create new transaction with failed status and user account balance not update
        //notify to transaction logger
        checkValidation(model);
        UserAccountModel userAccountModel = userAccountService.findById(model.getUserAccountId());
        if (userAccountModel == null)
            throw new BusinessException("user.not.found.with.id");
        if (userAccountModel.getBalance() < model.getAmount())
            throw new BusinessException("user.balance.is.less.than.amount.in.withdrawal");

        TransactionModel transactionModel = transactionService.create(model);
        userAccountModel.setBalance(userAccountModel.getBalance()- model.getAmount());
        userAccountService.update(userAccountModel);
        events.notify(transactionModel.getUserAccountId(), TransactionType.WITHDRAWAL.getTitle(), transactionModel.getAmount());
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
