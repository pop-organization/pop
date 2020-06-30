package com.pop.services;

import java.util.List;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;

public interface UpperCaseService {
	
	public OutPutInfo findText (String upperCaseDto);	
	public OutPutInfo insertText (UpperCase upperCaseDto);	
	
	public List<UpperCase> findAllText();	
}
