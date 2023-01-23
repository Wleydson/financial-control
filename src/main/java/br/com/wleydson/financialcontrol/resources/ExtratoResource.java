package br.com.wleydson.financialcontrol.resources;

import br.com.wleydson.financialcontrol.services.ExtractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/extract")
public class ExtratoResource {

    private final ExtractService extractService;

    @PostMapping
    public void uploadExtractFile(@RequestParam MultipartFile file) throws IOException {
        extractService.uploadExtract(file);
    }

}
