package com.cit.adm.address;

public class AddressNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AddressNotFoundException(String exception) {
		super(exception);
	}

}
