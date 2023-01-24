package br.com.wleydson.financialcontrol.model.vo;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExtractCardVO {

    private String day;
    private String month;
    private String description;
    private BigDecimal value;

    public ExtractCardVO(String lineExtract){
        String[] line = lineExtract.split(" ");
        int limitLine = lineExtract.split(" ").length - 1;
        this.day = line[0];
        this.month = line[1];
        this.value = new BigDecimal(line[limitLine].replace(".","").replace(",", ".").trim());
        this.description = lineExtract
                .replace(day, "")
                .replace(month, "")
                .replace(line[limitLine], "")
                .trim();
    }
}
