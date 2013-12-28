package com.easyjson.json.encoder.operation;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.easyjson.json.encoder.JSONEncoder;

public class ManageMapOperation implements IManagerOperation{
	private Object _obj;
	private Writer _out;
	private JSONEncoder _json;
		
	public ManageMapOperation(Object obj, Writer out, JSONEncoder json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() throws IOException{
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
