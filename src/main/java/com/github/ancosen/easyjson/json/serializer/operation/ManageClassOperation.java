package com.github.ancosen.easyjson.json.serializer.operation;

import java.io.Writer;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ancosen.easyjson.json.serializer.JSONSerializer;
import com.github.ancosen.easyjson.json.serializer.operation.introspection.IntrospectObject;

public class ManageClassOperation implements IManagerOperation{

	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
	private static Logger log = LoggerFactory.getLogger(ManageClassOperation.class);
	
	public ManageClassOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() {
		LinkedHashMap classMap = new LinkedHashMap();
		new IntrospectObject(_obj,classMap).analyze();
		new ParseValue(classMap, _out, _json).parseValue();
	}
	
}