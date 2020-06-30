package com.pop.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "words")
public class UpperCaseJpa implements Serializable {
	
	public UpperCaseJpa() {}		
	public UpperCaseJpa (String original, String modified) {
		this.original = original;
		this.modified = modified;
	}
		
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "id")
	private String id;
		
	@Column(name = "original")
	private String original;
	
	@Column(name = "modified")
	private String modified;
	
	@Column(name = "date_insert")
	private Date dateInsert;
	
	private static final long serialVersionUID = 1075978726650215522L;
}
