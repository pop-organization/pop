package com.pop.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutPutInfo {
	
	public OutPutInfo () {}
	public OutPutInfo (String info, String original) {
		this.info = info;
		this.original = original;
	}
	
	private String info;

	private String id;
	private String original;
	private String uppercase;
	
	private Date dateInsert;
}
