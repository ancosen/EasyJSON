package com.easyjson.json.serializer.operation;

import java.io.IOException;
import java.io.Writer;

import com.easyjson.json.serializer.JSONSerializer;

public class ManageStringOperation implements IManagerOperation{
	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
		
	public ManageStringOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() throws IOException{
		_json.quotationMarkJSONElement(_out);
		_out.write(String.valueOf(_obj));
		_json.quotationMarkJSONElement(_out);
	}
}
