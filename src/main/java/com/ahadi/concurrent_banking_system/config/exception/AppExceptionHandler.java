package com.ahadi.concurrent_banking_system.config.exception;

import com.ahadi.concurrent_banking_system.util.Translator;

public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(Translator.toLocale(e.getCause().getMessage()));
    }
}
