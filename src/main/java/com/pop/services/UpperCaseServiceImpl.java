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
			outPutInfo = mapper.mapToOutPutInfo(optional.get(), "String exists in database");										
		}
				
		return outPutInfo;
	}
	
	@Transactional
	@Override
	public OutPutInfo insertText (UpperCase upperCase) {
		OutPutInfo outPutInfo = findText(upperCase.getUppercase());		
		if (outPutInfo == null) {
			UpperCaseJpa upperCaseJpa = mapper.mapToUpperCaseJpa(upperCase);
			upperCaseRepository.save(upperCaseJpa);						
			outPutInfo = mapper.mapToOutPutInfo(upperCaseJpa, "Insert OK");
		} 				
		return outPutInfo;
	}
	
	@Override
	public List<UpperCase> findAllText() {
		List<UpperCaseJpa> upperCaseJpaList = upperCaseRepository.findAll();
		return mapper.mapToUpperCaseList(upperCaseJpaList);
	}

}
