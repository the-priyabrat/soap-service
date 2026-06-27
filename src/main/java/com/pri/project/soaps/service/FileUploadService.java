package com.pri.project.soaps.service;

import org.springframework.web.multipart.MultipartFile;

import com.pri.project.soaps.dto.ServiceResponse;

public interface FileUploadService {
	ServiceResponse uploadFile(MultipartFile file);
}
