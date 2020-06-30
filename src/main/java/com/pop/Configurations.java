package com.pop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;
import com.pop.jpa.UpperCaseJpa;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class Configurations {
	
	@Bean("orika")
	public MapperFacade orika () {
		MapperFactory mFactory = new DefaultMapperFactory.Builder().build();
		
		// mappings
		mFactory.classMap(UpperCaseJpa.class, UpperCase.class)
			.byDefault()
			.register();
		
		mFactory.classMap(UpperCaseJpa.class, OutPutInfo.class)
			.byDefault()
			.register();
		
		MapperFacade mapper = mFactory.getMapperFacade();
		
		return mapper;
	}

}
