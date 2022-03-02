package com.itechart.socialmediaservice.controller.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@PropertySource("classpath:messages/controller_messages.properties")
public class FileExceptionAdvice {
	@Value("${upload.file.failed}")
	private String fileUploadFailed;

}
