package br.com.wleydson.financialcontrol.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardExtractDTO {

    private Integer size;
    private BigDecimal value;
    private String name;

}
