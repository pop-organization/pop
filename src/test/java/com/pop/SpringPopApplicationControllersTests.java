package com.pop;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pop.controller.UpperCaseControllerImpl;
import com.pop.domain.UpperCase;
import com.pop.dto.ErrorOutPutDto;
import com.pop.dto.OutPutInfo;
import com.pop.repositories.UpperCaseRepository;
import com.pop.services.UpperCaseService;

@DisplayName("Tests controller layer for transformations to uppercase")
@WebMvcTest(controllers = UpperCaseControllerImpl.class)
public class SpringPopApplicationControllersTests {

	private OutPutInfo outPutInfo;	
	private MockHttpServletRequest request;
	private ErrorOutPutDto errorOutPutDto;

	@BeforeEach
	public void beforeAll() {
		errorOutPutDto = new ErrorOutPutDto("Text can't be null or empty");
				
		request = new MockHttpServletRequest();
		
		outPutInfo = new OutPutInfo();
		outPutInfo.setOriginal("hello");
		outPutInfo.setUppercase("HELLO");
		outPutInfo.setId("ff8081817304c3a7017304c43b020001");
		outPutInfo.setDateInsert(new Date());
	}

	@MockBean
	private UpperCaseService upperCaseService;


	@MockBean
	private UpperCaseRepository upperCaseRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("Insert text in Controller layer")
	@Test
	public void insertText_controller_test() throws JsonProcessingException, Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(upperCaseService.insertText(any(UpperCase.class))).thenReturn(outPutInfo);

		mockMvc
			.perform(post("/api/v1/transformations-to-uppercase/single-text")
			.param("text", "randomtext")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(objectMapper.writeValueAsString(outPutInfo)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.original", is(outPutInfo.getOriginal())))
			.andExpect(jsonPath("$.uppercase", is(outPutInfo.getUppercase())));			
	}

	@DisplayName("Find text in Controller layer")
	@Test
	public void findText_controller_test() throws JsonProcessingException, Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(upperCaseService.findText(any(String.class))).thenReturn(outPutInfo);
		
        mockMvc.perform(get("/api/v1/transformations-to-uppercase/single-text")
        	.param("text", "randomtext")
        	.contentType(MediaType.APPLICATION_JSON_VALUE)
        	.content(objectMapper.writeValueAsString(outPutInfo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.original", is(outPutInfo.getOriginal())))
            .andExpect(jsonPath("$.uppercase", is(outPutInfo.getUppercase())));
	}
	
	@DisplayName("Error text in Controller layer")
	@Test
	public void error_insertText_controller_test() throws JsonProcessingException, Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(upperCaseService.findText(any(String.class))).thenReturn(outPutInfo);
		
		outPutInfo.setInfo("String exists in database");
		
		mockMvc
			.perform(post("/api/v1/transformations-to-uppercase/single-text")
			.param("text", "")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(objectMapper.writeValueAsString(outPutInfo)))
			.andExpect(status().isIAmATeapot())
			.andExpect(jsonPath("$.errorMsg", is(errorOutPutDto.getErrorMsg())));
	}
}
