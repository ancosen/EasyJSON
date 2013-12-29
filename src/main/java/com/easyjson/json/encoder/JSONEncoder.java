package com.easyjson.json.encoder;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easyjson.common.IJSONCostants;
import com.easyjson.json.encoder.operation.IJSONEncodingOperation;
import com.easyjson.json.encoder.operation.ParseValue;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public class JSONEncoder extends HashMap implements IJSONEncodingOperation {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(JSONEncoder.class);

	public JSONEncoder() {
		super();
	}

	public static void toJSONString(LinkedHashMap map, Writer out) {		
        log.info("Start Creating JSON String");
        
		if (map == null)
			return;
		JSONEncoder json = new JSONEncoder();
		
		try {
			json.openJSONObject(out);
			
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				json.addJSONElement(entry.getKey(), entry.getValue(), out);
				if (iterator.hasNext()) {
					json.commaJSONElement(out);
				}
			}
			json.closeJSONObject(out);
			
		} catch (IOException e) {
	        log.error("IOException catched" + e);
		}
	}

	public void write(Object object, Object object2, Writer out)
			throws IOException {
		this.quotationMarkJSONElement(out);
		out.write(String.valueOf(object));
		this.quotationMarkJSONElement(out);
		this.colonJSONElement(out);
		new ParseValue(object2, out, this).parseValue();
	}

	public void addJSONElement(Object object, Object object2, Writer out)
			throws IOException {
		this.write(object, object2, out);
	}

	public void addJSONMapElement(Object object, Object object2, Writer out)
			throws IOException {
		this.write(object, object2, out);
	}

	public void openJSONObject(Writer out) throws IOException {
		out.write(IJSONCostants.START_BRACE);
	}

	public void closeJSONObject(Writer out) throws IOException {
		out.write(IJSONCostants.END_BRACE);
	}

	public void openJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.START_BRACKET);
	}

	public void closeJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.END_BRACKET);
	}

	public void commaJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.COMMA);
	}

	public void colonJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.COLON);
	}

	public void quotationMarkJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.DOUBLE_QUOTE);
	}
}
