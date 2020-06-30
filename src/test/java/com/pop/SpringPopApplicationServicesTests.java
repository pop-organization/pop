package com.pop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pop.domain.UpperCase;
import com.pop.dto.OutPutInfo;
import com.pop.jpa.UpperCaseJpa;
import com.pop.repositories.UpperCaseRepository;
import com.pop.services.UpperCaseServiceImpl;
import com.pop.utils.MapperUtil;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Tests service layer for transformations to uppercase")
@ExtendWith(MockitoExtension.class)
public class SpringPopApplicationServicesTests {

	private static UpperCaseJpa upperCaseJpa;
	private static OutPutInfo outPutInfo;
	private static UpperCase upperCase;
	
	@BeforeAll
	static void beforeAll() {
		upperCase = new UpperCase("hello");
				
		upperCaseJpa = new UpperCaseJpa("hello", "HELLO");
		upperCaseJpa.setId("ff8081817304c3a7017304c43b020001");
		upperCaseJpa.setDateInsert(new Date());
		
		outPutInfo = new OutPutInfo();
		outPutInfo.setOriginal("hello");
		outPutInfo.setUppercase("HELLO");
		outPutInfo.setId("ff8081817304c3a7017304c43b020001");
		outPutInfo.setDateInsert(upperCaseJpa.getDateInsert());	
	}
	
	@Mock
	private UpperCaseRepository upperCaseRepository;
	
	@Mock
	private MapperUtil mapper;
		
	@InjectMocks
	private UpperCaseServiceImpl upperCaseServiceImpl;
		
	@DisplayName("Find text in Service layer")
	@Test
	public void getText_service_test() {	
		when(upperCaseRepository.findByUppercase(any(String.class))).thenReturn(Optional.of(upperCaseJpa));						
		when(mapper.mapToOutPutInfo(upperCaseJpa, "String exists in database")).thenReturn(outPutInfo);
		
		OutPutInfo out = upperCaseServiceImpl.findText("HELLO");
		
		verify(mapper).mapToOutPutInfo(upperCaseJpa, "String exists in database"); 
		verify(upperCaseRepository).findByUppercase(any(String.class));
		
		assertEquals(outPutInfo.getUppercase(), out.getUppercase());
	}
	
	@DisplayName("Insert text in Service layer")
	@Test
	public void insertText_service_test() {	
		when(upperCaseRepository.findByUppercase(any(String.class))).thenReturn(null);		
		when(mapper.mapToUpperCaseJpa(upperCase)).thenReturn(upperCaseJpa);		
		when(mapper.mapToOutPutInfo(upperCaseJpa, "Insert OK")).thenReturn(outPutInfo);
		
		OutPutInfo out = upperCaseServiceImpl.insertText(upperCase);
		
		verify(upperCaseRepository).findByUppercase(any(String.class));
		verify(mapper).mapToUpperCaseJpa(upperCase);
		verify(upperCaseRepository).save(upperCaseJpa);
		
		assertEquals(out.getUppercase(), upperCaseJpa.getUppercase());				
	}
}
