package com.pop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pop.domain.UpperCase;
import com.pop.jpa.UpperCaseJpa;
import com.pop.repositories.UpperCaseRepository;
import com.pop.utils.OrikaMapper;

@Service
public class UpperCaseServiceImpl implements UpperCaseService {
	
	private UpperCaseRepository upperCaseRepository;	
	private OrikaMapper orika;
	
	@Autowired
	public UpperCaseServiceImpl (UpperCaseRepository upperCaseRepository,  OrikaMapper orika) {
		this.upperCaseRepository = upperCaseRepository;
		this.orika = orika;
	}	
	
	@Transactional
	@Override
	public UpperCase insertText (UpperCase upperCase) {
		UpperCase fromDd = findText(upperCase);		
		if (fromDd == null) {
			UpperCaseJpa upperCaseJpa = orika.getMapper().map(upperCase, UpperCaseJpa.class);
			upperCaseRepository.save(upperCaseJpa);			
			fromDd = orika.getMapper().map(upperCaseJpa, UpperCase.class);
		}
		
		return fromDd;
	}

	/**
	 * Find original text
	 */
	@Override
	public UpperCase findText (UpperCase upperCase) {
		UpperCase found = null;
				
		Optional<UpperCaseJpa> optional = upperCaseRepository.findByOriginal(upperCase.getOriginal());		
		if (optional.isPresent()) {
			found = orika.getMapper().map(optional.get(), UpperCase.class);
		}
				
		return found;
	}

}
