package com.projeto.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.service.ArquivoRepositoryService;
import com.projeto.service.CaminhoFile;


 
@RestController
@CrossOrigin
@RequestMapping(value = "/download")
public class RestDownloadService {
	
	@Autowired
	private ArquivoRepositoryService arquivoRepositoryService;
 	
	final String path = "C:\\imagens\\publico\\";
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<CaminhoFile>> listaImagen() {		
		return ResponseEntity.ok(arquivoRepositoryService.listaArquivos());
	}

	@RequestMapping(path = "/service-record", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {
		
		String fileName = arquivoRepositoryService.buscaArquivo(param);
		if (fileName != null) {
			String camino = "C:\\imagens\\publico\\" + fileName;
			File file = new File(camino);
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");        
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			System.out.println("Parametro pasado = " + param);

		    return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.parseMediaType("application/octet-stream"))
		            .body(resource);
		}
		return null;
		
	}
}