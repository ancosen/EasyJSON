package com.easyjson.json.serializer.operation;

import java.io.Writer;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public interface IJSONEncodingOperation {

	void addJSONElement(Object key, Object value, Writer out);
	
	void addJSONMapElement(Object object, Object object2, Writer out);
	
	void openJSONObject(Writer out);
	
	void closeJSONObject(Writer out);
	
	void openJSONElement(Writer out);
	 
	void closeJSONElement(Writer out);
	
	void commaJSONElement(Writer out);
	
	void colonJSONElement(Writer out);
	
	void quotationMarkJSONElement(Writer out);
}
