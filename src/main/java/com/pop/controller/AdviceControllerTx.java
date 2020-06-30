package com.pop.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pop.dto.ErrorOutPutDto;
import com.pop.dto.ErrorResponse;

import lombok.extern.log4j.Log4j2;

/**
 * Controller to handler some errors
 */
@ControllerAdvice
@Log4j2
public class AdviceControllerTx {
	
	private ObjectMapper jackson;
	
	@Autowired
	public AdviceControllerTx (ObjectMapper jackson) {
		this.jackson = jackson;
	}

	@ExceptionHandler({NullPointerException.class, Exception.class})
	public ResponseEntity<ErrorOutPutDto> handlerNullPointerAndException (Throwable th)  {
		log.info(th.getMessage());
		return new ResponseEntity<ErrorOutPutDto>(new ErrorOutPutDto(th.getMessage()), HttpStatus.EXPECTATION_FAILED);
	}
		
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse<String>> handlerRuntimeException (RuntimeException ex) throws JsonProcessingException {		
		ErrorResponse<String> e = new ErrorResponse<>(LocalDateTime.now(), HttpStatus.I_AM_A_TEAPOT.value(), ex.getMessage(), ex.getStackTrace()[0]);
		log.info(jackson.writeValueAsString(e));
		return new ResponseEntity<ErrorResponse<String>>(e, HttpStatus.I_AM_A_TEAPOT);
	}
	
}
