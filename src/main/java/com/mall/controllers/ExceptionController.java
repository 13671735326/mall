package com.mall.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mall.dtos.ErrorDTO;
import com.mall.exceptions.NotFoundExcption;

@ControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(NotFoundExcption.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorDTO notFound() {
		ErrorDTO dto = new ErrorDTO();
		dto.setErrorMessage("Resource not found!");
		return dto;
	}
}