package br.com.wleydson.financialcontrol.model;

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

    @Column(name = "description")
    private String description;

    @Column(name = "value_credit")
    private BigDecimal value;

    public CreditCardExtract(LocalDate date, String description, BigDecimal value){
        this.date = date;
        this.description = description;
        this.value = value;
    }
}
