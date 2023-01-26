package br.com.wleydson.financialcontrol.resources;

import br.com.wleydson.financialcontrol.model.CreditCardExtract;
import br.com.wleydson.financialcontrol.resources.dto.CreditCardExtractResumeDTO;
import br.com.wleydson.financialcontrol.services.impl.CreditCardExtractResourceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/credit-card-extract")
public class CreditCardExtractResource {

    private final CreditCardExtractResourceServiceImpl service;

    @PostMapping
    public void uploadExtractFile(@RequestParam MultipartFile file) throws IOException {
        service.uploadExtract(file);
    }

    @GetMapping
    public List<CreditCardExtract> findByMonthAndYear(@RequestParam String month, @RequestParam String year) {
        return service.findByMonthAndYear(month, year);
    }

    @GetMapping("/file")
    public List<CreditCardExtract> findByFile(@RequestParam String file) {
        return service.findByFile(file);
    }

    @GetMapping("/file/resume")
    public CreditCardExtractResumeDTO findByFileResume(@RequestParam String file) {
        return service.findByFileResume(file);
    }
}
