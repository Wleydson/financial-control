package br.com.wleydson.financialcontrol.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardExtractResumeDTO {

    private Integer size;
    private String file;
    private BigDecimal total;
    private List<CreditCardExtractDTO> extracts ;

    public CreditCardExtractResumeDTO(Integer size, String file, BigDecimal total) {
        this.size = size;
        this.file = file;
        this.total = total;
        this.extracts = new ArrayList<>();
    }

    public void addExtract(CreditCardExtractDTO dto){
        this.extracts.add(dto);
    }

    public List<CreditCardExtractDTO> getExtracts() {
        return extracts.stream().sorted((d1, d2) -> d2.getValue().compareTo(d1.getValue())).collect(Collectors.toList());
    }
}
