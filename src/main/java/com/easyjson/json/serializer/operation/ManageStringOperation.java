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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easyjson.json.serializer.JSONSerializer;

public class ManageStringOperation implements IManagerOperation{
	private Object _obj;
	private Writer _out;
	private JSONSerializer _json;
	private static Logger log = LoggerFactory.getLogger(ManageStringOperation.class);
		
	public ManageStringOperation(Object obj, Writer out, JSONSerializer json) {
		super();
		_obj = obj;
		_out = out;
		_json = json;
	}
	
	public void exec(){
		_json.quotationMarkJSONElement(_out);
		try {
			_out.write(String.valueOf(_obj));
		} catch (IOException e) {
			log.error("Method: exec -" + e);
			return;
		}
		_json.quotationMarkJSONElement(_out);
	}
}
