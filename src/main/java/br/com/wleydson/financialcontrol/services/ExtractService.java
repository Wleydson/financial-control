package br.com.wleydson.financialcontrol.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExtractService {

    void uploadExtract(MultipartFile file) throws IOException;
}
