package br.com.wleydson.financialcontrol.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    public static LocalDate parseToDate(String date) {
        if (Objects.isNull(date)) {
            return null;
        }

        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (DateTimeParseException e){
            throw new RuntimeException();
        }
    }
}
