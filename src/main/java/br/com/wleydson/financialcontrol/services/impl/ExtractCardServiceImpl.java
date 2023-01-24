package br.com.wleydson.financialcontrol.services.impl;

import br.com.wleydson.financialcontrol.model.vo.ExtractCardVO;
import br.com.wleydson.financialcontrol.services.ExtractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtractCardServiceImpl implements ExtractService {

    private final String monthsrRegex = "/JAN|FEVMARABR|AIO|UNJUL|GO|SET|OUT|NOV|DEZ/gi";
    private final String moneyRegex = ",";
    private final String firstNumberRegex = "^[0-9]";

    @Override
    public void uploadExtract(MultipartFile file) throws IOException {
        List<ExtractCardVO> extracts = new ArrayList();
        try (PDDocument document = PDDocument.load(file.getBytes())) {

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                String lines[] = pdfFileInText.split("\\r?\\n");
                Pattern patternMonth = Pattern.compile(monthsrRegex);
                Pattern patternMoney = Pattern.compile(moneyRegex);
                Pattern patternFirstNumber = Pattern.compile(firstNumberRegex);
                for (String line : lines) {
                    if(patternFirstNumber.matcher(line).find() && patternMonth.matcher(line).find() && patternMoney.matcher(line).find()) {
                        System.out.println(line);
                        extracts.add(new ExtractCardVO(line));
                    }
                }
            }
        }

        extracts.forEach(e -> System.out.println(e));
    }
}

