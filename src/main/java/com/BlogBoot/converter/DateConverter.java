package com.BlogBoot.converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateConverter {

    public static long asEpochMillis(LocalDate localDate) {
        return localDate != null
                ? localDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond() * 1000
                : 0;
    }
    public static long asEpochMillis(LocalDateTime dateTime) {
        return dateTime != null
                ? dateTime.atOffset(ZoneOffset.UTC).toEpochSecond() * 1000
                : 0;
    }

    public static LocalDate asLocalDate(long epochMillis) {
        return LocalDate.ofEpochDay(epochMillis/86_400_000);
    }

    public static LocalDateTime asLocalDateTime(long epochMillis) {
        return LocalDateTime.ofEpochSecond(epochMillis/1000, 0, ZoneOffset.UTC);
    }

}
