package br.com.wleydson.financialcontrol.repository;

import br.com.wleydson.financialcontrol.model.CreditCardExtract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CreditCardExtractRepository extends JpaRepository<CreditCardExtract, Integer> {

    List<CreditCardExtract> findByMonthAndYear(String month, String year);

    List<CreditCardExtract> findByFile(String file);

}
