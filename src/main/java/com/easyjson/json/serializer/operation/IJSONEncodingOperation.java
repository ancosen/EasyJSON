package com.easyjson.json.serializer.operation;

import java.io.Writer;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public interface IJSONEncodingOperation {

	void addJSONElement(Object key, Object value, Writer out);
}
