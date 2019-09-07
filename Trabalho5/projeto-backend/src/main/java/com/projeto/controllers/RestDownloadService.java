package com.projeto.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
@CrossOrigin
@RequestMapping(value = "/download")
public class RestDownloadService {
 	
	final String path = "C:\\imagens\\publico\\teste2.jpg";

	@RequestMapping(path = "/service-record", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {
		
		String camino = "C:\\imagens\\publico\\" + param;
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
}