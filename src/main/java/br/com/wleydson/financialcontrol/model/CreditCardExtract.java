package br.com.wleydson.financialcontrol.model;

import br.com.wleydson.financialcontrol.enums.BankEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credit_card_extract")
public class CreditCardExtract {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDIT_CARD_EXTRACT_SEQ")
    @SequenceGenerator(name = "CREDIT_CARD_EXTRACT_SEQ", sequenceName = "CREDIT_CARD_EXTRACT_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "date_credit")
    private LocalDate date;

    @Column(name = "month_extract")
    private String month;

    @Column(name = "year_extract")
    private String year;

    @Column(name = "description")
    private String description;

    @Column(name = "file")
    private String file;

    @Column(name = "value_credit")
    private BigDecimal value;

    @Column(name = "bank")
    @Enumerated(EnumType.STRING)
    private BankEnum bank;

    public CreditCardExtract(LocalDate date, String month, String year, String description, BigDecimal value, BankEnum bank, String file){
        this.date = date;
        this.month = month;
        this.year = year;
        this.description = description;
        this.value = value;
        this.bank = bank;
        this.file = file;
    }
}
