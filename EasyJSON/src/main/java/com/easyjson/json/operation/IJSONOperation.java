package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;

public interface IJSONOperation {

	void addJSONElement(Object key, Object value, Writer out) throws IOException;
}
