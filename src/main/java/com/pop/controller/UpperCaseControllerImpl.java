package com.pop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pop.domain.UpperCase;
import com.pop.services.UpperCaseService;

import lombok.val;
import lombok.extern.log4j.Log4j2;
import static com.pop.controller.UpperCaseController.*;

@Log4j2
@RestController
@RequestMapping("api/v1/transformations")
public class UpperCaseControllerImpl {
		
	private UpperCaseService upperCaseService;
	
	@Autowired
	public UpperCaseControllerImpl (UpperCaseService upperCaseService) {
		this.upperCaseService = upperCaseService;
	}
	
	/**
	 * endPoint for test
	 * @return
	 */
	@GetMapping("/ping")
	public String ping() {
		return "OK";
	}
	
	/**
	 * Insert a uppercase text in database
	 */
	@PostMapping
	public ResponseEntity<UpperCase> insertText (@RequestParam("text") String text) {
		log.info(INPUT_CREATE, text);							
		val upperCaseDto = upperCaseService.insertText(new UpperCase(text));			
		return new ResponseEntity<UpperCase>(upperCaseDto, HttpStatus.OK);
	}
	
	/**
	 * Search for text in database
	 */
	@GetMapping
	public ResponseEntity<UpperCase> findText (@RequestParam("text") String text) {
		log.info(INPUT_SEARCH, text);
		val upperCaseDto = upperCaseService.findText(new UpperCase(text));		
		return new ResponseEntity<UpperCase>(upperCaseDto, HttpStatus.OK);
	}
		
}
