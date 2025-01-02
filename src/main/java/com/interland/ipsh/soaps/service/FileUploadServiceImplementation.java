package com.interland.ipsh.soaps.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.interland.ipsh.soaps.dto.ServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class FileUploadServiceImplementation implements FileUploadService {

	@Value("${image.upload.path}")
	String directoryPath;

	@Override
	public ServiceResponse uploadFile(MultipartFile file) {
		try {
			String name = file.getOriginalFilename();
			byte[] randomArray = new byte[10];
			for (int i = 0; i < randomArray.length; i++) {
				byte random = (byte) ((byte) 97 + Math.random() * (122 - 97) + 1);
				randomArray[i] = random;
			}
			System.out.println(randomArray[0]);
			String generatedFileName = new String(randomArray, StandardCharsets.UTF_8);
			System.out.println(generatedFileName);
			File fileToUpload = new File(directoryPath + File.separator + "Files");
			String uploadPath = directoryPath;
			if (fileToUpload.exists()) {
				fileToUpload.mkdir();
			}
			Long count = Files.copy(file.getInputStream(), Paths.get(uploadPath + File.separator + generatedFileName
					+ name.substring(name.indexOf('.'), name.length())));
			if (count > 0) {
				return new ServiceResponse(name, "File Uploaded", Collections.emptyList());
			} else {
				throw new IOException("File not saved");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ServiceResponse("Error", "File Upload Failed", Collections.emptyList());
	}

}
