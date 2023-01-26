package br.com.wleydson.financialcontrol.services.impl;

import br.com.wleydson.financialcontrol.enums.BankEnum;
import br.com.wleydson.financialcontrol.enums.MonthsEnum;
import br.com.wleydson.financialcontrol.model.CreditCardExtract;
import br.com.wleydson.financialcontrol.repository.CreditCardExtractRepository;
import br.com.wleydson.financialcontrol.resources.dto.CreditCardExtractDTO;
import br.com.wleydson.financialcontrol.resources.dto.CreditCardExtractResumeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardExtractResourceServiceImpl {

    private final CreditCardExtractRepository repository;

    public List<CreditCardExtract> findByMonthAndYear(String month, String year){
        return repository.findByMonthAndYear(month, year);
    }

    public List<CreditCardExtract> findByFile(String file){
        return repository.findByFile(file);
    }

    public CreditCardExtractResumeDTO findByFileResume(String file){
        List<CreditCardExtract> extracts = repository.findByFile(file);
        Map<String, List<CreditCardExtract>> groupCreditCardExtract = extracts.stream().collect(groupingBy(CreditCardExtract::getDescription));
        CreditCardExtractResumeDTO dto = new CreditCardExtractResumeDTO(extracts.size(), file, extracts.stream().map(e -> e.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add));
        groupCreditCardExtract.forEach((name, credit) ->
                dto.addExtract(new CreditCardExtractDTO(credit.size(), credit.stream().map(e -> e.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add), name))
        );
        return dto;
    }

    public void uploadExtract(MultipartFile file) throws IOException {
        List<CreditCardExtract> extracts = new ArrayList();
        String yearCredit = getYearCredit(file);
        try (PDDocument document = PDDocument.load(file.getBytes())) {
            if (!document.isEncrypted()) {
                extracts = getLines(document).stream()
                        .filter(l -> isValidLine(l))
                        .map(l -> convertLineInCreditCardExtract(l, yearCredit, file.getOriginalFilename()))
                        .collect(Collectors.toList());
            }
        }

        repository.saveAll(extracts);
    }

    private String getYearCredit(MultipartFile file) {
        return file.getOriginalFilename().split("_")[1].split("-")[0];
    }

    private List<String> getLines(PDDocument document) throws IOException {
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        PDFTextStripper tStripper = new PDFTextStripper();
        String pdfFileInText = tStripper.getText(document);
        return Arrays.asList(pdfFileInText.split("\\r?\\n"));
    }

    private boolean isValidLine(String line) {
        String monthsrRegex = "JAN|FEV|MAR|ABR|MAIO|JUN|JUL|AGO|SET|OUT|NOV|DEZ";
        String moneyRegex = ",";
        String firstNumberRegex = "^[0-9]";

        Pattern patternMonth = Pattern.compile(monthsrRegex);
        Pattern patternMoney = Pattern.compile(moneyRegex);
        Pattern patternFirstNumber = Pattern.compile(firstNumberRegex);

        return patternFirstNumber.matcher(line).find() && patternMonth.matcher(line).find() && patternMoney.matcher(line).find();
    }

    private CreditCardExtract convertLineInCreditCardExtract(String line, String year, String file) {
        String[] lineSplit = line.split(" ");
        int limitLine = line.split(" ").length - 1;

        String  day = lineSplit[0];
        String month = lineSplit[1];
        BigDecimal value = new BigDecimal(lineSplit[limitLine].replace(".","").replace(",", ".").trim());
        String description = line.replace(day, "").replace(month, "").replace(lineSplit[limitLine], "").trim();

        return   new CreditCardExtract(getDate(year, day, month), month, year, description, value, BankEnum.NUBANK, file);
    }

    private static LocalDate getDate(String year, String day, String month) {
        return LocalDate.of(Integer.parseInt(year), MonthsEnum.getByAbbreviation(month).getMonthOrder(), Integer.parseInt(day));
    }
}
