package com.github.ancosen.easyjson.json.serializer.mock;

import java.util.ArrayList;
import java.util.List;

public class Group {

	String group = "";
	
	List<PersonSimple> personList = new ArrayList();

	public Group(String group, List<PersonSimple> personList) {
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

	public List<PersonSimple> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonSimple> personList) {
		this.personList = personList;
	}
}
