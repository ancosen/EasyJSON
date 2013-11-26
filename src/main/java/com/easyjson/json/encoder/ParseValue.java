package com.easyjson.json.encoder;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

public class ParseValue {

	private Object _obj;
	private Writer _out;
	private JSONEncoder _json;
	
	public ParseValue(Object obj, Writer out, JSONEncoder json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void parseValue() throws IOException {
		if (_obj instanceof String) {
			manageString();
		} 
		else if (_obj instanceof List) {
			manageList();
		} 
		else if (_obj instanceof Map){
			manageMap();
		}
		else _out.write(String.valueOf(_obj));
	}
	
	public void manageString() throws IOException{
		_json.quotationMarkJSONElement(_out);
		_out.write(String.valueOf(_obj));
		_json.quotationMarkJSONElement(_out);
	}
	
	public void manageList() throws IOException{
		_json.openJSONObject(_out);
		Iterator iterator = ((List) _obj).iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof String) {
				_json.quotationMarkJSONElement(_out);
				_out.write(String.valueOf(element));
				_json.quotationMarkJSONElement(_out);
			} 
			else {
				_out.write(String.valueOf(element));
			}
			if (iterator.hasNext()) {
				_json.commaJSONElement(_out);
			}
		}
		_json.closeJSONObject(_out);
	}
	
	public void manageMap() throws IOException{
		Iterator iterator = ((HashMap) _obj).entrySet().iterator();
		_json.openJSONElement(_out);
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			_json.addJSONMapElement(entry.getKey(), entry.getValue(), _out);
			if (iterator.hasNext()) {
				_json.commaJSONElement(_out);
			}
		}
		_json.closeJSONElement(_out);
	}
}
