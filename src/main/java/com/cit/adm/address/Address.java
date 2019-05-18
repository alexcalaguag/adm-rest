package com.cit.adm.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	@Column(name="address")
	private String addressDescription;
	@Column(name="type")
	private String addressType;
	@Column(name="personid")
	private Long personId;

	public Address() {
		super();
	}

	public Address(Long id, String addressDescription, String addressType, Long personId) {
		super();
		this.id = id;
		this.addressDescription = addressDescription;
		this.addressType = addressType;
		this.personId = personId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}



}
