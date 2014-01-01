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

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.easyjson.json.serializer.JSONSerializer;

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
