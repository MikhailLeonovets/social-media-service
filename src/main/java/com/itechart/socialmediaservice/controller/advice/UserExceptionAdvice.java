package com.itechart.socialmediaservice.controller.advice;

import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:messages/controller_messages.properties")
public class UserExceptionAdvice {
	@Value("${upload.file.empty}")
	private String userNotFoundMsg;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
		return ResponseEntity.badRequest().body(userNotFoundMsg);
	}
}
