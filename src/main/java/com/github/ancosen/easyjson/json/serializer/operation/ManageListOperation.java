package com.github.ancosen.easyjson.json.serializer.operation;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ancosen.easyjson.json.serializer.JSONSerializer;
import com.github.ancosen.easyjson.json.serializer.utils.Utils;

public class ManageListOperation implements IManagerOperation{

	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
	private static Logger log = LoggerFactory.getLogger(ManageListOperation.class);
	
	public ManageListOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec() {
		_json.openJSONElement(_out);
		Iterator iterator = ((List) _obj).iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			if (element instanceof String) {
				_json.quotationMarkJSONElement(_out);
				try {
					_out.write(String.valueOf(element));
				} catch (IOException e) {
					log.error("Method: exec -" + e);
					return;
				}
				_json.quotationMarkJSONElement(_out);
			} 
			else if (element instanceof Map) {
				new ManageMapOperation((Map) element, _out, _json).exec();
			} 
			else if (element instanceof List) {
				new ManageListOperation((List) element, _out, _json).exec();
			} 
			else if (!(Utils.isJDKClass(element))){
				new ManageClassOperation(element, _out, _json).exec();
			}
			else {
				try {
					_out.write(String.valueOf(element));
				} catch (IOException e) {
					log.error("Method: exec -" + e);
					return;
				}
			}
			if (iterator.hasNext()) {
				_json.commaJSONElement(_out);
			}
		}
		_json.closeJSONElement(_out);
	}
}
