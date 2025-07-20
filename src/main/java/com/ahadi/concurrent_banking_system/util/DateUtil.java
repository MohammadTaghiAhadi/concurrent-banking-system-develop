package com.ahadi.concurrent_banking_system.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateUtil {

    public Date longToDate(Long longValue)
    {
        if (longValue == null)
            return null;
        else return new Date(longValue);
    }

    public Long dateToLong(Date date)
    {
        if (date != null)
            return date.getTime();
        else
            return null;
    }


}
