package com.pop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;
import com.pop.services.UpperCaseService;

import lombok.val;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1/transformations-to-uppercase")
public class UpperCaseControllerImpl implements UpperCaseController {
		
	private UpperCaseService upperCaseService;
	
	@Autowired
	public UpperCaseControllerImpl (UpperCaseService upperCaseService) {
		this.upperCaseService = upperCaseService;
	}
	
	@GetMapping("/ping")
	@Override
	public String ping() {
		return "OK";
	}
	
	@PostMapping("/single-text")
	@Override
	public ResponseEntity<OutPutInfo> insertText (@RequestParam("text") String text) {
		log.info(INPUT_CREATE, text);							
		val upperCaseDto = upperCaseService.insertText(new UpperCase(text));			
		return new ResponseEntity<OutPutInfo>(upperCaseDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/single-text")
	@Override
	public ResponseEntity<OutPutInfo> findText (@RequestParam("text") String text) {
		log.info(INPUT_SEARCH, text);
		val upperCaseDto = upperCaseService.findText(new UpperCase(text).getUppercase());		
		return new ResponseEntity<OutPutInfo>(upperCaseDto, HttpStatus.OK);
	}
	
	@GetMapping
	@Override
	public ResponseEntity<List<UpperCase>> findAllText (String text) {
		log.info(INPUT_SEARCH_ALL, text);
		List<UpperCase> upperCaseList = upperCaseService.findAllText();
		return new ResponseEntity<List<UpperCase>>(upperCaseList, HttpStatus.OK);
	}
		
}
