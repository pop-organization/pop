package com.pop.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;
import com.pop.jpa.UpperCaseJpa;

import ma.glasnost.orika.MapperFacade;

@Component
public class MapperUtil {
	
	private MapperFacade orika;
	
	@Autowired
	public MapperUtil (MapperFacade orika) {
		this.orika = orika;
	}
	
	public OutPutInfo mapToOutPutInfo (UpperCaseJpa upperCaseJpa, String info) {
		OutPutInfo outPutInfo = orika.map(upperCaseJpa, OutPutInfo.class);
		outPutInfo.setInfo(info);
		return outPutInfo;
	}
	
	public UpperCaseJpa mapToUpperCaseJpa (UpperCase upperCase) {
		UpperCaseJpa upperCaseJpa = orika.map(upperCase, UpperCaseJpa.class);
		return upperCaseJpa;
	}
	
	public List<UpperCase> mapToUpperCaseList(List<UpperCaseJpa> upperCaseJpaList) {
		List<UpperCase> upperCaseList = orika.mapAsList(upperCaseJpaList, UpperCase.class);
		return upperCaseList;
	}
}
