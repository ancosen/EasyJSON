package com.easyjson.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
		list1.add("aaa");
		list1.add("nnn");
		list1.add("ccc");
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

	public void testComplex2() throws IOException {
		StringWriter out = new StringWriter();
		Map m1 = new LinkedHashMap();
		List l2 = new LinkedList();
		List l3 = new LinkedList();
		List l1 = new LinkedList();

		l2.add("v11");
		l2.add("v12");
		l2.add("v13");
		l3.add("v21");
		l3.add("v22");
		l3.add("v23");
		m1.put("root", l2);
		m1.put("root1", l3);
		JSONEncoder.toJSONString(m1, out);
		System.out.println(out);
	}
	
	public void testComplex3() throws IOException {
		StringWriter out = new StringWriter();
		Map m1 = new LinkedHashMap();
		List l2 = new LinkedList();
		List l3 = new LinkedList();
		List l1 = new LinkedList();

		l2.add("v11");
		l2.add("v12");
		l2.add("v13");
		l3.add("v21");
		l3.add("v22");
		l3.add("v23");
		l1.add("v21");
		l1.add("v22");
		l1.add("v23");
		m1.put("root1", l1);
		m1.put("root2", l2);
		m1.put("root3", l3);
		JSONEncoder.toJSONString(m1, out);
		System.out.println(out);
	}

}
