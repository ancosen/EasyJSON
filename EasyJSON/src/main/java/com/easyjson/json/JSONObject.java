package com.easyjson.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.easyjson.common.IJSONCostants;

public class JSONObject extends HashMap implements IJSONOperation {

	private static final long serialVersionUID = -503443796854799292L;

	public JSONObject() {
		super();
	}

	public static void writeJSONString(Map map, Writer out) throws IOException {
		JSONObject json = new JSONObject();
		JSONElement<String, String> jsonElement = new JSONElement<String, String>("ID", "28");
		JSONElement<Integer, Integer> jsonElementNew = new JSONElement<Integer, Integer>(new Integer(1),new Integer(1));
		json.openJSONObject(out);
		json.addJSONElement(jsonElement.getKey(),jsonElement.getValue(), out);
		json.addJSONElement((Integer) jsonElementNew.getKey(),(Integer) jsonElementNew.getValue(), out);
		json.closeJSONObject(out);
	}
	
	public void openJSONObject(Writer out) throws IOException{
		out.write(IJSONCostants.LEFT_SQUARE_BRACKET);
	}
	
	public void closeJSONObject(Writer out) throws IOException{
		out.write(IJSONCostants.RIGHT_SQUARE_BRACKET);
	}
	
	public void openJSONElement(Writer out) throws IOException{
		out.write(IJSONCostants.LEFT_CURLY_BRACKET);
	}
	
	public void closeJSONElement(Writer out) throws IOException{
		out.write(IJSONCostants.RIGHT_CURLY_BRACKET);
	}
	
	public void commaJSONElement(Writer out) throws IOException{
		out.write(IJSONCostants.COMMA);
	}
	
	public void colonJSONElement(Writer out) throws IOException{
		out.write(IJSONCostants.COLON);
	}
	
	public void quotationMarkJSONElement(Writer out) throws IOException{
		out.write(IJSONCostants.QUOTATION_MARK);
	}
	
	public void write(String object, String object2, Writer out) throws IOException{
		this.quotationMarkJSONElement(out);
		out.write(object);
		this.quotationMarkJSONElement(out);
		this.colonJSONElement(out);
		out.write(object2);
	}
	
	public void write(Integer object, Integer object2, Writer out) throws IOException{
		this.quotationMarkJSONElement(out);
		out.write(object.toString());
		this.quotationMarkJSONElement(out);
		this.colonJSONElement(out);
		out.write(object2.toString());
	}

	public void addJSONElement(String key, String value, Writer out) throws IOException {
		this.openJSONElement(out);
		this.write(key, value, out);
		this.closeJSONElement(out);
	}
	
	public void addJSONElement(Integer key, Integer value, Writer out) throws IOException {
		this.openJSONElement(out);
		this.write(key, value, out);
		this.closeJSONElement(out);
	}

}
