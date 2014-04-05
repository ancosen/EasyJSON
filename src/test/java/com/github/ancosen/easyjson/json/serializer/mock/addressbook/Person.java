package com.github.ancosen.easyjson.json.serializer.mock.addressbook;

import java.util.List;

public class Person {

	private String name;
	
	private String surname;
	
	private int age;
	
	private Address address;
	
	private List phoneNumber;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(List phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", age=" + age
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
