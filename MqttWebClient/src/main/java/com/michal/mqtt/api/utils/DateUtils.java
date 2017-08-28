package com.michal.mqtt.api.utils;

import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date getDateFromText(@RequestParam String dateFrom) {
        String[] dates = dateFrom.split("-");
        LocalDate localDate = LocalDate.of(Integer.valueOf(dates[0]), Integer.valueOf(dates[1]), Integer.valueOf(dates[2]));
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
