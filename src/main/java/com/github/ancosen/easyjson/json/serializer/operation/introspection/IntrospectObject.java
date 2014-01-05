package com.github.ancosen.easyjson.json.serializer.operation.introspection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IntrospectObject {

	private Object _obj;

	public IntrospectObject(Object obj) {
		super();
		this._obj = obj;
	}

	public void analyze(){
		Field[] field = _obj.getClass().getDeclaredFields();
		List p = Arrays.asList(field);
		Iterator iter = p.iterator();
		while(iter.hasNext()){
			Field localField= (Field) iter.next();
			localField.setAccessible(true);
			System.err.println(localField.getName());
				Object object = null;
				try {
					object = localField.get(_obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println(object.getClass());
				System.err.println(object);
		}
	}
	
	
}
