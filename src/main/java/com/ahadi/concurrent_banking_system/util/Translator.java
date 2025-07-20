package com.ahadi.concurrent_banking_system.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    public Translator(@Qualifier("textsResourceBundleMessageSource") ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public static String toLocale(String code) {
        Locale locale = new Locale("fa");
        return messageSource.getMessage(code, null, locale);
    }
}
