package com.easyjson.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import junit.framework.TestCase;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 * 
 */

public class ComplexEncodingTest extends TestCase {

	public void testComplex1() throws IOException {
		StringWriter out = new StringWriter();
		Map obj = new LinkedHashMap();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		LinkedList list = new LinkedList();
		LinkedList list1 = new LinkedList();
		list1.add(11);
		list1.add(12);
		list1.add(13);
		list.add(list1);
		list.add("foo");
		list.add(new Integer(100));
		list.add(new Double(1000.21));
		list.add(new Boolean(true));
		list.add(null);
		obj.put("testList", list);
		JSONEncoder.toJSONString(obj, out);
		System.out.println(out);
	}

}
