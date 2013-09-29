package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;

public interface IJSONOperation {

	void addJSONElement(String key, String value, Writer out) throws IOException;
	
	void addJSONElement(Integer key, Integer value, Writer out) throws IOException;
}
