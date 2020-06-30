package com.pop.services;

import com.pop.domain.UpperCase;

public interface UpperCaseService {
	
	public UpperCase findText (UpperCase upperCaseDto);	
	public UpperCase insertText (UpperCase upperCaseDto);	
}
