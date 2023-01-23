package br.com.wleydson.financialcontrol.services.impl;

import br.com.wleydson.financialcontrol.model.Extract;
import br.com.wleydson.financialcontrol.repository.ExtractRepository;
import br.com.wleydson.financialcontrol.services.ExtractService;
import br.com.wleydson.financialcontrol.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExtractServiceImpl implements ExtractService {

    private final ExtractRepository repository;

    @Override
    public void uploadExtract(MultipartFile file) {
        List<Extract> extracts = getExtracts(file);
        extracts.forEach(e -> save(e));
    }

    @Transactional
    protected void save(Extract extract){
        if (repository.existsByIdentifierAndValue(extract.getIdentifier(), extract.getValue())){
            log.info("Informação já cadastrada: identifier={} value={} ", extract.getIdentifier(), extract.getValue());
            return;
        }
        repository.save(extract);
    }

    private List<Extract> getExtracts(MultipartFile file) {
        Pattern pattern = Pattern.compile(",");
        List<Extract> extracts;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            extracts = in.lines().skip(1)
                    .map(line -> {
                        String[] x = pattern.split(line);
                        return new Extract(DateUtil.parseToDate(x[0]), new BigDecimal(x[1]), x[2], x[3], file.getOriginalFilename());
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return extracts;
    }

}

