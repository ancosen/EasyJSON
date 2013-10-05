package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.easyjson.common.IJSONCostants;
import com.easyjson.json.operation.IJSONOperation;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public class JSONEncoder extends HashMap implements IJSONOperation {

	private static final long serialVersionUID = 1L;

	public JSONEncoder() {
		super();
	}

	public static void writeJSONString(Map map, Writer out) throws IOException {
		if (map == null)
			return;
		JSONEncoder json = new JSONEncoder();
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
		this.openJSONElement(out);
		this.write(object, object2, out);
		this.closeJSONElement(out);
	}

	public void addJSONMapElement(Object object, Object object2, Writer out)
			throws IOException {
		this.write(object, object2, out);
	}

	public void openJSONObject(Writer out) throws IOException {
		out.write(IJSONCostants.LEFT_SQUARE_BRACKET);
	}

	public void closeJSONObject(Writer out) throws IOException {
		out.write(IJSONCostants.RIGHT_SQUARE_BRACKET);
	}

	public void openJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.LEFT_CURLY_BRACKET);
	}

	public void closeJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.RIGHT_CURLY_BRACKET);
	}

	public void commaJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.COMMA);
	}

	public void colonJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.COLON);
	}

	public void quotationMarkJSONElement(Writer out) throws IOException {
		out.write(IJSONCostants.QUOTATION_MARK);
	}
}
