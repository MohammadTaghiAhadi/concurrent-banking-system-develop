package com.ahadi.concurrent_banking_system.config;

import com.ahadi.concurrent_banking_system.config.exception.AppExceptionHandler;

import java.util.concurrent.ThreadFactory;

public class AppThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new AppExceptionHandler());
        return thread;
    }
}
