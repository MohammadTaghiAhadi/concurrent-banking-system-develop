package com.ahadi.concurrent_banking_system.util;

import com.ahadi.concurrent_banking_system.config.core.BusinessException;
import com.ahadi.concurrent_banking_system.service.UserAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

@Component
public class Util {

    final UserAccountService userAccountService;
    @Value("${user.account.random.number.length}")
    private Integer length;

    static final public String fileName="transaction_log.txt";

    public Util(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public String GenerateRandomNumber() {
        String randomNumber = String.valueOf(length < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, length - 1)) - 1)
                + (int) Math.pow(10, length - 1));
        try {
            if (userAccountService.findByUserAccountNumber(randomNumber) != null)
                GenerateRandomNumber();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return randomNumber;
    }

    @Async
    public void createTextFile(String log){
        File yourFile = new File(fileName);
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.write(Paths.get(fileName), log.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
