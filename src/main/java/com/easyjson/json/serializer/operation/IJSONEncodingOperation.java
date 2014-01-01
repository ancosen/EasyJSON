/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
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
