package com.github.ancosen.easyjson.json.serializer.mock;

import java.util.Iterator;
import java.util.Map.Entry;

public class Example {

	int id;
	String name;

	public Example(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append("{");
	    result.append("id=" + id);
	    result.append(", ");
	    result.append("name=" + name);
	    result.append("}");
	    
	    return result.toString();
	}

}
