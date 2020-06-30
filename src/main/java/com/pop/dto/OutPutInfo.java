package com.pop.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutPutInfo {
	
	private String info;

	private String id;
	private String original;
	private String uppercase;
	
	private Date dateInsert;
}
