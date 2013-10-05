package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.easyjson.common.IJSONCostants;

public class JSONObject extends HashMap implements IJSONOperation {

	private static final long serialVersionUID = -503443796854799292L;

	public JSONObject() {
		super();
	}

	public static void writeJSONString(Map map, Writer out) throws IOException {
		JSONObject json = new JSONObject();
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

	public void write(Object object, Object object2, Writer out)
			throws IOException {
		this.quotationMarkJSONElement(out);
		out.write(String.valueOf(object));
		this.quotationMarkJSONElement(out);
		this.colonJSONElement(out);
		this.parseValue(object2, out);
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

	public void parseValue(Object obj, Writer out) throws IOException {
		if (obj instanceof String) {
			this.quotationMarkJSONElement(out);
			out.write(String.valueOf(obj));
			this.quotationMarkJSONElement(out);
		} else if (obj instanceof List) {
			this.openJSONObject(out);
			Iterator iterator = ((List) obj).iterator();
			while (iterator.hasNext()) {
				Object element = iterator.next();
				if (element instanceof String) {
					this.quotationMarkJSONElement(out);
					out.write(String.valueOf(element));
					this.quotationMarkJSONElement(out);
				} else {
					out.write(String.valueOf(element));
				}
				if (iterator.hasNext()) {
					this.commaJSONElement(out);
				}
			}
			this.closeJSONObject(out);
		} 
		else if (obj instanceof Map){
			Iterator iterator = ((HashMap) obj).entrySet().iterator();
			this.openJSONElement(out);
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				this.addJSONMapElement(entry.getKey(), entry.getValue(), out);
				if (iterator.hasNext()) {
					this.commaJSONElement(out);
				}
			}
			this.closeJSONElement(out);
		}
		else out.write(String.valueOf(obj));
	}

}
