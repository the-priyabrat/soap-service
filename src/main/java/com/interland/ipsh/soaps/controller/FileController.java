package com.interland.ipsh.soaps.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.interland.ipsh.soaps.dto.ServiceResponse;
import com.interland.ipsh.soaps.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {
	
	private final FileUploadService service;
	
	@PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ServiceResponse> uploadFile(@RequestPart MultipartFile file){
		ServiceResponse response = service.uploadFile(file);
		return new ResponseEntity<>(response,new HttpHeaders(),HttpStatus.OK);
	} 
}
