package com.pop.utils;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pop.domain.UpperCase;
import com.pop.jpa.UpperCaseJpa;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaMapper {

	private MapperFacade mapper;

	@PostConstruct
	private void definitionsMappers() {
		MapperFactory mFactory = new DefaultMapperFactory.Builder().build();
		
		// mappings
		mFactory.classMap(UpperCaseJpa.class, UpperCase.class)
			.byDefault()
			.register();
		
		mapper = mFactory.getMapperFacade();
	}

	public MapperFacade getMapper() {
		return mapper;
	}
}
