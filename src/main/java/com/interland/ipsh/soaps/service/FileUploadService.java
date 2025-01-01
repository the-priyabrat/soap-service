package com.interland.ipsh.soaps.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.interland.ipsh.soaps.dto.ServiceResponse;

public interface FileUploadService {
	ServiceResponse uploadFile(MultipartFile file);
}
