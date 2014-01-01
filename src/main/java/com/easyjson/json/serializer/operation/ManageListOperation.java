package com.easyjson.json.serializer.operation;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.easyjson.json.serializer.JSONSerializer;

public class ManageListOperation implements IManagerOperation{

	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
		
	public ManageListOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() throws IOException{
		_json.openJSONElement(_out);
		Iterator iterator = ((List) _obj).iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof String) {
				_json.quotationMarkJSONElement(_out);
				_out.write(String.valueOf(element));
				_json.quotationMarkJSONElement(_out);
			} 
			else if (element instanceof Map) {
				new ManageMapOperation((Map) element, _out, _json).exec();
			} 
			else if (element instanceof List) {
				new ManageListOperation((List) element, _out, _json).exec();
			} 
			else {
				_out.write(String.valueOf(element));
			}
			if (iterator.hasNext()) {
				_json.commaJSONElement(_out);
			}
		}
		_json.closeJSONElement(_out);
	}
}
