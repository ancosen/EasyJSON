package com.github.ancosen.easyjson.json.serializer.mock;

import java.util.Date;

public class PersonSimple {
	
	private String name = "";
	
	private String surname = "";
	
	private String taxcode = "";
	
	private String birthDate = "";

	
	public PersonSimple(String name, String surname, String taxcode, Date birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.taxcode = taxcode;
		this.birthDate = birthDate.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}
