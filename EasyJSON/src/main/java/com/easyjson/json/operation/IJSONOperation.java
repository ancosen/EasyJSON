package com.easyjson.json.operation;

import java.io.IOException;
import java.io.Writer;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public interface IJSONOperation {

	void addJSONElement(Object key, Object value, Writer out)
			throws IOException;
}
