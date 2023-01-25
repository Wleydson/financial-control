package br.com.wleydson.financialcontrol.resources;

import br.com.wleydson.financialcontrol.services.CreditCardExtractResourceService;
import br.com.wleydson.financialcontrol.services.ExtractService;
import br.com.wleydson.financialcontrol.services.impl.CreditCardExtractResourceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

}
