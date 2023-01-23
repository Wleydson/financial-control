package br.com.wleydson.financialcontrol.repository;

import br.com.wleydson.financialcontrol.model.Extract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ExtractRepository extends JpaRepository<Extract, Integer> {

    boolean existsByIdentifierAndValue(String identifier, BigDecimal value);

}
