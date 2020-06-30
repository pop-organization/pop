package com.pop.controller;

import org.springframework.http.ResponseEntity;

import com.pop.domain.UpperCase;

public interface UpperCaseController {

	String INPUT_CREATE = "Req to ws insertText ('api/v1/transformation') with text:{}";
	String INPUT_SEARCH = "Req to ws findText ('api/v1/transformations') with text:{}";	
			
	ResponseEntity<UpperCase> insertText (String text);
	ResponseEntity<UpperCase> findText (String text);
}
