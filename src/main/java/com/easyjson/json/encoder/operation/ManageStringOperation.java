package com.easyjson.json.encoder.operation;

import java.io.IOException;
import java.io.Writer;

import com.easyjson.json.encoder.JSONEncoder;

public class ManageStringOperation implements IManagerOperation{
	private Object _obj;
	private Writer _out;
	private JSONEncoder _json;
		
	public ManageStringOperation(Object obj, Writer out, JSONEncoder json) {
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
