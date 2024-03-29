package com.itechart.socialmediaservice.controller.advice;

import com.itechart.socialmediaservice.service.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:messages/controller_messages.properties")
public class FileExceptionAdvice {
	@Value("${upload.file.failed}")
	private String fileUploadFailed;

	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<?> handleFileUploadException(FileUploadException e) {
		return ResponseEntity.badRequest().body(fileUploadFailed);
	}
}
