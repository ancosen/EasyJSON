/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.easyjson.json.serializer.operation;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easyjson.json.serializer.JSONSerializer;

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
