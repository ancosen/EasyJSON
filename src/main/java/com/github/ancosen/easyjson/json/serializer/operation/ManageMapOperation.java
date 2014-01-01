package com.github.ancosen.easyjson.json.serializer.operation;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.github.ancosen.easyjson.json.serializer.JSONSerializer;

public class ManageMapOperation implements IManagerOperation{
	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
		
	public ManageMapOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec(){
		Iterator iterator = ((HashMap) _obj).entrySet().iterator();
		_json.openJSONObject(_out);
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			_json.addJSONMapElement(entry.getKey(), entry.getValue(), _out);
			if (iterator.hasNext()) {
				_json.commaJSONElement(_out);
			}
		}
		_json.closeJSONObject(_out);
	}
}
