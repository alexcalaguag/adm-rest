package com.cit.adm.person;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String exception) {
		super(exception);
	}

}
