package com.interland.ipsh.soaps.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.interland.ipsh.soaps.dto.ServiceResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploadServiceImplementation implements FileUploadService {

	@Value("${image.upload.path}")
	String directoryPath;

	/*
	 * public ServiceResponse uploadFile1() { try { File file = new
	 * File("C:\\Users\\User1\\Documents\\product data.csv"); String name =
	 * "priyabrat.csv"; File fileToUpload = new File(directoryPath
	 * +File.separator+"Files"); String uploadPath = directoryPath; if
	 * (!fileToUpload.exists()) { fileToUpload.mkdir(); } InputStream inputStream =
	 * new FileInputStream(file); Long count =
	 * Files.copy(inputStream,Paths.get(uploadPath+File.separator+name)); } catch
	 * (Exception e) { e.printStackTrace(); System.out.println(e.getMessage()); }
	 * return null; }
	 */
	@Override
	public ServiceResponse uploadFile(MultipartFile file) {
		try {
			String name = file.getName();
			File fileToUpload = new File(directoryPath +File.separator+"Files");
			String uploadPath = directoryPath;
			if (!fileToUpload.exists()) {
				fileToUpload.mkdir();
			}
			Long count = Files.copy(file.getInputStream(),Paths.get(uploadPath+File.separator+name));
		} catch(Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
