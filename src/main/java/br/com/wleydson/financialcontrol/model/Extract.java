package br.com.wleydson.financialcontrol.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "extract")
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXTRACT_SEQ")
    @SequenceGenerator(name = "EXTRACT_SEQ", sequenceName = "EXTRACT_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "date_extract")
    private LocalDate date;

    @Column(name = "value_extract")
    private BigDecimal value;

    @Column(name = "identifier", unique = true)
    private String identifier;

    @Column(name = "description")
    private String description;

    @Column(name = "file_name")
    private String fileName;

    public Extract(LocalDate date, BigDecimal value, String identifier, String description, String fileName) {
        this.date = date;
        this.value = value;
        this.identifier = identifier;
        this.description = description;
        this.fileName = fileName;
    }

}
