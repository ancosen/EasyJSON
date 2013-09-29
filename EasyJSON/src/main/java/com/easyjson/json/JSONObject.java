package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.easyjson.common.IJSONCostants;

public class JSONObject extends HashMap implements Map {

	private static final long serialVersionUID = -503443796854799292L;

	public JSONObject() {
		super();
	}

	public static void writeJSONString(Map map, Writer out) throws IOException {
		if (map == null) {
			out.write("null");
			return;
		}
		out.write(IJSONCostants.LEFT_SQUARE_BRACKET);
		out.write(IJSONCostants.LEFT_CURLY_BRACKET);
		out.write(IJSONCostants.QUOTATION_MARK);
		out.write("Id");
		out.write(IJSONCostants.QUOTATION_MARK);
		out.write(IJSONCostants.COLON);
		out.write(IJSONCostants.QUOTATION_MARK);
		out.write("10");
		out.write(IJSONCostants.QUOTATION_MARK);		
		out.write(IJSONCostants.RIGHT_CURLY_BRACKET);
		out.write(IJSONCostants.RIGHT_SQUARE_BRACKET);
	}

}
