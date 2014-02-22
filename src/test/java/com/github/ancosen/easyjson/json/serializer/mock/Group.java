package com.github.ancosen.easyjson.json.serializer.mock;

import java.util.ArrayList;
import java.util.List;

public class Group {

	String group = "";
	
	List<Person> personList = new ArrayList();

	public Group(String group, List<Person> personList) {
		super();
		this.group = group;
		this.personList = personList;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
}
