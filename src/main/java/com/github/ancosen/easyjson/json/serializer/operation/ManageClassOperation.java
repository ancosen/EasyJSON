package com.github.ancosen.easyjson.json.serializer.operation;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ancosen.easyjson.json.serializer.JSONSerializer;
import com.github.ancosen.easyjson.json.serializer.operation.introspection.IntrospectObject;

public class ManageClassOperation implements IManagerOperation{

	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
	private static Logger log = LoggerFactory.getLogger(ManageListOperation.class);
	
	public ManageClassOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() {
		System.err.println("Class: " + _obj.getClass());
		new IntrospectObject(_obj).analyze();
	}
	
}