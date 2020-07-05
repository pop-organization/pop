package com.pop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;
import com.pop.jpa.UpperCaseJpa;
import com.pop.repositories.UpperCaseRepository;
import com.pop.utils.MapperUtil;

@Service
public class UpperCaseServiceImpl implements UpperCaseService {
	
	private UpperCaseRepository upperCaseRepository;	
	private MapperUtil mapper;
	
	private static final String NOT_EXISTS_DB = "String is not exists in database";
	private static final String EXISTS = "String exists in database";
	private static final String INSERT_OK = "Insert OK";
	
	@Autowired
	public UpperCaseServiceImpl (UpperCaseRepository upperCaseRepository,  MapperUtil mapperUtil) {
		this.upperCaseRepository = upperCaseRepository;
		this.mapper = mapperUtil;
	}	
	
	@Override
	public OutPutInfo findText (String uppercase) {
		OutPutInfo outPutInfo = null;
				
		Optional<UpperCaseJpa> optional = upperCaseRepository.findByUppercase(uppercase);		
		if (optional != null && optional.isPresent()) {			
			outPutInfo = mapper.mapToOutPutInfo(optional.get(), EXISTS);										
		} else  {
			outPutInfo = new OutPutInfo(NOT_EXISTS_DB, uppercase.toLowerCase());
		}
				
		return outPutInfo;
	}
	
	@Transactional
	@Override
	public OutPutInfo insertText (UpperCase upperCase) {
		OutPutInfo outPutInfo = findText(upperCase.getUppercase());		
		if (outPutInfo.getId() == null) {
			UpperCaseJpa upperCaseJpa = mapper.mapToUpperCaseJpa(upperCase);
			upperCaseRepository.save(upperCaseJpa);						
			outPutInfo = mapper.mapToOutPutInfo(upperCaseJpa, INSERT_OK);
		} 				
		return outPutInfo;
	}
	
	@Override
	public List<UpperCase> findAllText() {
		List<UpperCaseJpa> upperCaseJpaList = upperCaseRepository.findAll();
		return mapper.mapToUpperCaseList(upperCaseJpaList);
	}

}
