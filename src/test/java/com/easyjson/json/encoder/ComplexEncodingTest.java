package com.easyjson.json.encoder;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.easyjson.json.encoder.JSONEncoder;

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

	public void testComplex4() throws IOException {
		StringWriter out = new StringWriter();
		Map obj = new LinkedHashMap();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		JSONEncoder.toJSONString(obj, out);
		System.out.println(out);
	}

	public void testComplex5() throws IOException {
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		for (int i = 0; i < 1000; i++) {
			p.add(i);
		}

		Collection c = new ArrayList();
		String p1 = "Ciao";
		for (int f = 0; f < 1000; f++) {
			c.add(p1);
		}

		Map obj1 = new HashMap();
		obj1.put("name", "foo");
		obj1.put("num", new Integer(100));
		obj1.put("balance", new Double(1000.21));
		obj1.put("list", p);

		Map obj2 = new HashMap();
		obj2.put("is_vip", new Boolean(true));
		obj2.put("nickname", null);
		obj2.put("anotherlist", c);
		obj2.putAll(obj1);

		JSONEncoder.toJSONString(obj2, out);
		System.out.println(out);
	}

	public void testComplex6() throws IOException {
		StringWriter out = new StringWriter();

		List list1 = new ArrayList();
		list1.add("foo");
		list1.add(new Integer(100));
		list1.add(new Double(1000.21));

		List list2 = new ArrayList();
		list2.add(new Boolean(true));
		list2.add(null);

		Map obj = new HashMap();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);

		obj.put("list1", list1);
		obj.put("list2", list2);

		JSONEncoder.toJSONString(obj, out);
		System.out.println(out);
	}

}
