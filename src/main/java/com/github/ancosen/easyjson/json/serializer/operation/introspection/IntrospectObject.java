package com.github.ancosen.easyjson.json.serializer.operation.introspection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntrospectObject {

	private Object _obj;
	private Map _classMap;
	private static Logger log = LoggerFactory.getLogger(IntrospectObject.class);

	public IntrospectObject(Object obj, Map aClassMap) {
		super();
		this._obj = obj;
		this._classMap = aClassMap;
	}

	public void analyze(){
		Field[] field = _obj.getClass().getDeclaredFields();
		List p = Arrays.asList(field);
		Iterator iter = p.iterator();
		while(iter.hasNext()){
			Field localField= (Field) iter.next();
			localField.setAccessible(true);
				Object object = new Object();
				try {
					object = localField.get(_obj);
				} catch (IllegalArgumentException e) {
					log.error("Method: analyze -" + e);
					return;
				} catch (IllegalAccessException e) {
					log.error("Method: analyze -" + e);
					return;
				}
			 _classMap.put(localField.getName(),object);
		}
	}
	
	
}
