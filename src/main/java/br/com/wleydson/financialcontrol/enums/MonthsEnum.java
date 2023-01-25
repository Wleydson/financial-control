package br.com.wleydson.financialcontrol.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MonthsEnum {

    JANUARY(1, "JAN"),
    FEBRUARY(2, "FEV"),
    MARCH(3, "MAR"),
    APRIL(4, "ABR"),
    MAY(5, "MAIO"),
    JUNE(6, "JUN"),
    JULY(7, "JUL"),
    AUGUST(8, "AGO"),
    SEPTEMBER(9, "SET"),
    OCTOBER(10, "OUT"),
    NOVEMBER(11, "NOV"),
    DECEMBER(12, "DEZ");

    private Integer monthOrder;

    private String abbreviation;

    public static MonthsEnum getByAbbreviation(String description){
        return Arrays.asList(MonthsEnum.values()).stream().filter(m -> m.getAbbreviation().equalsIgnoreCase(description)).findAny().orElse(null);
    }
}
