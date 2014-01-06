package com.github.ancosen.easyjson.json.serializer.mock;

import java.util.List;
import java.util.Map;

public class ComplexExample {

	int id;
	String name;
	List p;
	Map s;

	public ComplexExample(int id, String name, List p, Map s) {
		super();
		this.id = id;
		this.name = name;
		this.p = p;
		this.s = s;
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

	public List getP() {
		return p;
	}

	public void setP(List p) {
		this.p = p;
	}

	public Map getS() {
		return s;
	}

	public void setS(Map s) {
		this.s = s;
	}

	@Override
    public String toString() {
		StringBuilder result = new StringBuilder();

	    result.append("{");
	    result.append("id=" + id);
	    result.append(", ");
	    result.append("name=" + name);
	    result.append(", ");
	    result.append("p=" + p);
	    result.append(", ");
	    result.append("s=" + s);
	    result.append("}");
	    
	    return result.toString();
	}
}
