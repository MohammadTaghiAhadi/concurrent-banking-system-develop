package com.ahadi.concurrent_banking_system.logic;

import com.ahadi.concurrent_banking_system.config.AppThreadFactory;
import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.desing_pattern.decorator.base.TransactionDecorator;
import com.ahadi.concurrent_banking_system.desing_pattern.decorator.impl.DepositTransaction;
import com.ahadi.concurrent_banking_system.desing_pattern.decorator.impl.WithdrawalTransaction;
import com.ahadi.concurrent_banking_system.enums.TransactionType;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.service.TransactionService;
import com.ahadi.concurrent_banking_system.service.TransferAmountService;
import com.ahadi.concurrent_banking_system.service.UserAccountService;
import com.ahadi.concurrent_banking_system.util.Util;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Bank {

    // Bank class that manages user accounts. It should provide methods for creating
    //accounts, performing transactions, and retrieving account information

    final UserAccountService userAccountService;
    final TransactionService transactionService;
    final TransferAmountService transferAmountService;
    final Util util;


    ExecutorService executorService = Executors.newCachedThreadPool(new AppThreadFactory());

    public Bank(UserAccountService userAccountService, TransactionService transactionService,
                TransferAmountService transferAmountService, Util util) {
        this.userAccountService = userAccountService;
        this.transactionService = transactionService;
        this.transferAmountService = transferAmountService;
        this.util = util;
    }

    public UserAccountModel createUserAccount(UserAccountModel userAccountModel) throws BusinessException, ConstraintViolationException {
        userAccountModel.setAccountNumber(util.GenerateRandomNumber());
       return userAccountService.create(userAccountModel);
    }

    public String getUserByAccountNumber(Long userAccountId) throws BusinessException{
        return userAccountService.findById(userAccountId).toString();
    }

    public List<TransactionModel> getTransactionListByUserAccountId(Long userAccountId) throws BusinessException{
        return transactionService.findAllByUserAccountId(userAccountId);
    }
    public void deposit(TransactionModel transactionModel) throws BusinessException,ConstraintViolationException{
        TransactionDecorator transactionDecorator = new DepositTransaction(transactionService,userAccountService);
        executorService.execute(() ->
        {
            try {
                transactionDecorator.doTransaction(transactionModel);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void withdrawal(TransactionModel transactionModel) throws BusinessException,ConstraintViolationException{
        TransactionDecorator transactionDecorator = new WithdrawalTransaction(transactionService,userAccountService);
        executorService.execute(() ->
        {
            try {
                transactionDecorator.doTransaction(transactionModel);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Transactional(rollbackOn = Exception.class)
    public void transfer(TransferAmountModel transferAmountModel) throws BusinessException,ConstraintViolationException{
        executorService.execute(() ->
        {
            try {
                TransactionDecorator transactionDecorator =new WithdrawalTransaction(transactionService,userAccountService);
                TransactionModel withdrawalModel=TransactionModel.builder()
                        .transactionType(TransactionType.WITHDRAWAL)
                        .amount(transferAmountModel.getAmount())
                        .userAccountId(transferAmountModel.getWithdrawalId())
                        .build();
                transactionDecorator.doTransaction(withdrawalModel);


                transactionDecorator = new DepositTransaction(transactionService,userAccountService);
                TransactionModel depositModel=TransactionModel.builder()
                        .transactionType(TransactionType.DEPOSIT)
                        .amount(transferAmountModel.getAmount())
                        .userAccountId(transferAmountModel.getDepositorId())
                        .build();
                transactionDecorator.doTransaction(depositModel);

                transferAmountService.create(transferAmountModel);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
