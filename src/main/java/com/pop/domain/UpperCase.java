package com.pop.domain;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.Date;

import com.pop.utils.PopException;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain entity in DDD context
 */
@Getter
@Setter
public class UpperCase implements Serializable {
	private static final int MAX_LONG = 50;
	private static final String ERROR_MAX_LONG = "Text can't be greater than %d";
	private static final String EMPTY_NULL = "Text can't be null or empty";
	
	public UpperCase() {}
	public UpperCase (String original) {
		convertToLower(original);
		this.modified = original.toUpperCase();
	}

	private String id;
	private String original;
	private String modified;
	private Date dateInsert;
	
	/**
	 * Eval if not null and not empty
	 * Eval if not longer than max_long cte
	 * @param original
	 */
	private void convertToLower (String original) {
		if (isNotBlank(original)) {
			if (original.length() < MAX_LONG) {
				this.original = original.toLowerCase();
			} else throw new PopException(format(ERROR_MAX_LONG, MAX_LONG));
		} else throw new PopException(EMPTY_NULL);
	}

	private static final long serialVersionUID = -5086536416237570097L;
}
