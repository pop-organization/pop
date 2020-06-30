package com.pop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;

public interface UpperCaseController {

	String INPUT_CREATE = "Req to ws insertText ('api/v1/transformation') with text:{}";
	String INPUT_SEARCH = "Req to ws findText ('api/v1/transformations') with text:{}";	
	String INPUT_SEARCH_ALL = "Req to ws findAllText ('api/v1/transformations')";	
	
	String ping();
	
	/**
	 * Insert a uppercase text in database
	 */
	ResponseEntity<OutPutInfo> insertText (String text);
	
	/**
	 * Search for text in database
	 */
	ResponseEntity<OutPutInfo> findText (String text);
	
	/**
	 * Search for all texts in database
	 */
	ResponseEntity<List<UpperCase>> findAllText (String text);
}
