package com.ahadi.concurrent_banking_system;

import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.enums.TransactionType;
import com.ahadi.concurrent_banking_system.logic.Bank;
import com.ahadi.concurrent_banking_system.model.TransactionModel;
import com.ahadi.concurrent_banking_system.model.TransferAmountModel;
import com.ahadi.concurrent_banking_system.model.UserAccountModel;
import com.ahadi.concurrent_banking_system.util.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan("com.ahadi.concurrent_banking_system.*")
public class ConcurrentBankingSystemApplication{

	private static Logger LOG = LoggerFactory
			.getLogger(ConcurrentBankingSystemApplication.class);

	final Bank bank;


	private static Scanner in = new Scanner(System.in);

	public ConcurrentBankingSystemApplication( Bank bank) {
		this.bank = bank;
	}

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(ConcurrentBankingSystemApplication.class, args);
		LOG.info("APPLICATION STARTED");
	}

//	@Override
//	public void run(String... args) throws Exception {
//		cliInput();
//
//	}

	private void cliInput(){
		System.out.println("Please Select Operation");
		System.out.println("1:Create New Account");
		System.out.println("2:Create Transaction");
		System.out.println("3:Transfer Amount");
		System.out.println("4:User Information");
		System.out.println("5:Transaction List");
		int operation = Integer.parseInt(in.next());
		operations(operation);
	}

	private void operations(int operation) {
		UserAccountModel userAccountModel = new UserAccountModel();
		TransactionModel transactionModel = new TransactionModel();
		TransferAmountModel transferAmountModel = new TransferAmountModel();
		switch (operation){
			case 1:
				createUserAccount(userAccountModel);
				break;
			case 2:
				createTransaction(transactionModel);
				break;
			case 3:
				createTransfer(transferAmountModel);
				break;
			case 4:
				getUserInformation();
				break;
			case 5:
				getUserTransactions();
				break;
		}
	}

	private void getUserTransactions() {
		System.out.println("###### Account ID:");
		try {
			List<TransactionModel> transactionList = bank.getTransactionListByUserAccountId(in.nextLong());
			transactionList.forEach(transaction -> {
				System.out.println(transaction.toString());
			});
			cliInput();
		} catch (BusinessException e) {
			System.out.println(Translator.toLocale(e.getMessage()));
			System.out.println("###### Error Has Been Occurred!!");
		}
		cliInput();
	}

	private void getUserInformation() {
		System.out.println("###### Account ID:");
		try {
			System.out.println(bank.getUserByAccountNumber(in.nextLong()));
		} catch (BusinessException e) {
			System.out.println(Translator.toLocale(e.getMessage()));
			System.out.println("###### Error Has Been Occurred!!");
		}
		cliInput();
	}

	private void createTransfer(TransferAmountModel transferAmountModel) {
		System.out.println("###### Withdrawal Account ID:");
		transferAmountModel.setWithdrawalId(in.nextLong());
		System.out.println("###### Depositor Account ID:");
		transferAmountModel.setDepositorId(in.nextLong());
		System.out.println("###### Amount:");
		transferAmountModel.setAmount(in.nextDouble());
		try {
			bank.transfer(transferAmountModel);
			System.out.println("###### Transfer Done!!");
			cliInput();
		} catch (BusinessException e) {
			System.out.println(Translator.toLocale(e.getMessage()));
			System.out.println("###### Transfer Failed!!");
		} catch (ConstraintViolationException e){
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
					.map(constraintViolation -> String.format("%s", Translator.toLocale(constraintViolation.getMessage())))
					.collect(Collectors.toList()));
			StringBuilder stringBuilder = new StringBuilder();
			messages.forEach(stringBuilder::append);
			System.out.println(stringBuilder);
		}
	}

	private void createTransaction(TransactionModel transactionModel) {
		try {
			System.out.println("###### Account ID:");
			transactionModel.setUserAccountId(in.nextLong());
			System.out.println("###### Transaction Type(d=deposit,w=withdrawal):");
			checkTransactionType(transactionModel);
			System.out.println("###### Amount:");
			transactionModel.setAmount(in.nextDouble());
			if (transactionModel.getTransactionType().equals(TransactionType.DEPOSIT))
				bank.deposit(transactionModel);
			else
				bank.withdrawal(transactionModel);
			System.out.println("###### Transaction Finish!!");
			cliInput();
		} catch (BusinessException e) {
			System.out.println(Translator.toLocale(e.getMessage()));
			System.out.println("###### Transaction Failed!!");
		} catch (ConstraintViolationException e){
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
					.map(constraintViolation -> String.format("%s", Translator.toLocale(constraintViolation.getMessage())))
					.collect(Collectors.toList()));
			StringBuilder stringBuilder = new StringBuilder();
			messages.forEach(stringBuilder::append);
			System.out.println(stringBuilder);
		}
	}

	private void createUserAccount(UserAccountModel userAccountModel) {
		System.out.println("###### Account Holder Name:");
		userAccountModel.setHolderName(in.next());
		System.out.println("###### Initial Balance:");
		userAccountModel.setBalance(in.nextDouble());
		try {
			userAccountModel = bank.createUserAccount(userAccountModel);
			System.out.println("###### User Account Created");
			System.out.println("###### User Account Number:"+ userAccountModel.getAccountNumber());
			System.out.println("###### User Account ID:"+ userAccountModel.getId());
			cliInput();
		} catch (BusinessException e) {
			System.out.println(Translator.toLocale(e.getMessage()));
			System.out.println("###### User Account Creation Failed");
		} catch (ConstraintViolationException e){
			Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

			Set<String> messages = new HashSet<>(constraintViolations.size());
			messages.addAll(constraintViolations.stream()
					.map(constraintViolation -> String.format("%s", Translator.toLocale(constraintViolation.getMessage())))
					.collect(Collectors.toList()));
			StringBuilder stringBuilder = new StringBuilder();
			messages.forEach(stringBuilder::append);
			System.out.println(stringBuilder);
		}
	}

	private void checkTransactionType(TransactionModel transactionModel) {
		String type=in.next();
		if (type.equals("d"))
			transactionModel.setTransactionType(TransactionType.DEPOSIT);
		else if (type.equals("w"))
			transactionModel.setTransactionType(TransactionType.WITHDRAWAL);
		else {
			System.out.println("###### Please Enter d or w only");
			checkTransactionType(transactionModel);
		}
	}
}
