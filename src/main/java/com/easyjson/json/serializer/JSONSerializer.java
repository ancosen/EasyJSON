package com.easyjson.json.serializer;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easyjson.common.IJSONCostants;
import com.easyjson.json.serializer.operation.IJSONEncodingOperation;
import com.easyjson.json.serializer.operation.ParseValue;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 * 
 */

public class JSONSerializer extends HashMap implements IJSONEncodingOperation {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(JSONSerializer.class);

	public JSONSerializer() {
		super();
	}

	public static void toJSONString(LinkedHashMap map, Writer out) {
		log.info("Start Serializing JSON Object");

		if (map == null)
			return;
		JSONSerializer json = new JSONSerializer();

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
		
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("Method: toJSONString -" + e);
			return;
		}

		log.info("End  Serializing JSON Object");
	}

	public void write(Object object, Object object2, Writer out) {
		try {
			this.quotationMarkJSONElement(out);
			out.write(String.valueOf(object));
			this.quotationMarkJSONElement(out);
			this.colonJSONElement(out);
			new ParseValue(object2, out, this).parseValue();
		} catch (IOException e) {
			log.error("Method: write -" + e);
			return;
		}
	}

	public void addJSONElement(Object object, Object object2, Writer out) {
		this.write(object, object2, out);
	}

	public void addJSONMapElement(Object object, Object object2, Writer out) {
		this.write(object, object2, out);
	}

	public void openJSONObject(Writer out) {
		try {
			out.write(IJSONCostants.START_BRACE);
		} catch (IOException e) {
			log.error("Method: openJSONObject -" + e);
			return;
		}
	}

	public void closeJSONObject(Writer out) {
		try {
			out.write(IJSONCostants.END_BRACE);
		} catch (IOException e) {
			log.error("Method: closeJSONObject -" + e);
			return;
		}
	}

	public void openJSONElement(Writer out) {
		try {
			out.write(IJSONCostants.START_BRACKET);
		} catch (IOException e) {
			log.error("Method: openJSONElement -" + e);
			return;
		}
	}

	public void closeJSONElement(Writer out) {
		try {
			out.write(IJSONCostants.END_BRACKET);
		} catch (IOException e) {
			log.error("Method: closeJSONElement -" + e);
			return;
		}

	}

	public void commaJSONElement(Writer out) {
		try {
			out.write(IJSONCostants.COMMA);
		} catch (IOException e) {
			log.error("Method: commaJSONElement -" + e);
			return;
		}
	}

	public void colonJSONElement(Writer out) {
		try {
			out.write(IJSONCostants.COLON);
		} catch (IOException e) {
			log.error("Method: colonJSONElement -" + e);
			return;
		}
	}

	public void quotationMarkJSONElement(Writer out) {
		try {
			out.write(IJSONCostants.DOUBLE_QUOTE);
		} catch (IOException e) {
			log.error("Method: quotationMarkJSONElement -" + e);
			return;
		}
	}
}
