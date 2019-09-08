package com.projeto.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.service.ArquivoRepositoryService;

@RestController
@CrossOrigin
@RequestMapping(value = "/upload")
public class RestUploadService {
	
	@Autowired
	private ArquivoRepositoryService arquivoRepositoryService;
	
	String caminhoSalvo = "C:\\imagens\\publico\\";
	
	@RequestMapping(path = "/service-record", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
		storeFile(file);
		return ResponseEntity.ok("Foi");
	}
	
	public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);
        try {
        	String path = "";
            if(fileName.contains("..")) {
            	System.out.println("não foi salvo");
            }
            path = caminhoSalvo.concat(fileName);
            Path targetLocation = Paths.get(path);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            arquivoRepositoryService.savaArquivo(fileName);
            return fileName;
        } catch (IOException ex) {
        	System.out.println("não foi salvo 222");
        	ex.printStackTrace();
        }
        return "";
    }

}
