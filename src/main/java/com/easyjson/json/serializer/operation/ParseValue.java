package com.easyjson.json.serializer.operation;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.easyjson.json.serializer.JSONSerializer;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public class ParseValue {

	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
	
	public ParseValue(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void parseValue() throws IOException {
		if (_obj instanceof String) {
			new ManageStringOperation(_obj, _out, _json).exec();
		} 
		else if (_obj instanceof List) {
			new ManageListOperation(_obj, _out, _json).exec();
		} 
		else if (_obj instanceof Map){
			new ManageMapOperation(_obj, _out, _json).exec();
		}
		else _out.write(String.valueOf(_obj));
	}
}
