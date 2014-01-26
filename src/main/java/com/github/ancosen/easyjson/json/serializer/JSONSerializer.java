package com.github.ancosen.easyjson.json.serializer;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ancosen.easyjson.common.IJSONCostants;
import com.github.ancosen.easyjson.json.serializer.operation.IJSONEncodingOperation;
import com.github.ancosen.easyjson.json.serializer.operation.ParseValue;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 * 
 */

/**
 * Writes a JSON serialized String from a Map to a Writer.
 */
public class JSONSerializer extends HashMap implements IJSONEncodingOperation {

    /**
     * Slf4j Logger
     */
	private static Logger log = LoggerFactory.getLogger(JSONSerializer.class);

	public JSONSerializer() {
		super();
	}

	/**
	 * Build a JSON object in a Writer. 
	 * The LinkedHashMap argument must specify a Map to serialize in JSON. 
	 * The Writer argument is a Writer to build the output JSON String. 
	 * <p>
	 *
	 * @param  map a Map to serialize in JSON
	 * @param  out a Writer to build the JSON String
	 * @return      
	 */
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
