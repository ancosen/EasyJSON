package com.easyjson.json.encoder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncondingTest{

	@Test
	public void testNull() throws IOException{
		StringWriter out = new StringWriter();
		Map map = null;
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
	}
	
	@Test	
	public void testEmpty() throws IOException{
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().equals("{}"));
	}
	
	@Test	
	public void test1() throws IOException {
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		map.put("Id", 29);
		map.put("type","MIME");
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Id"));
		assertTrue(out.toString().contains("29"));
		assertTrue(out.toString().contains("type"));
		assertTrue(out.toString().contains("MIME"));
	}

	@Test
	public void test2() throws IOException {
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		p.add("Start");
		p.add(22.12);
		p.add(-26.19);
		List p1 = new ArrayList();
		p1.add("Hello");
		p1.add("Ciao");
		p1.add("Salut");
		Map map = new HashMap();
		map.put("Id", 29);
		map.put("List1", p);
		map.put("List2", p1);
		map.put("type","MIME");
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Start"));
		assertTrue(out.toString().contains("22.12"));
		assertTrue(out.toString().contains("-26.19"));
		assertTrue(out.toString().contains("Hello"));
		assertTrue(out.toString().contains("Ciao"));
		assertTrue(out.toString().contains("Salut"));
		assertTrue(out.toString().contains("List1"));
	}

	@Test
	public void test3() throws IOException{
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		Map map3 = new HashMap();
		map3.put("Param1", "a.html");
		map3.put("Param2", "b.html");
		map3.put("HTML Page", "HTML");
		map2.put("HTML", map3);
		map2.put("Param1", "a");
		map2.put("Param2", "b");
		map2.put("Servlet-Name", "ServletExe");
		map1.put("Id", 29);
		map1.put("Servlet", map2);
		map.put("Root",map1);
		
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Param1"));
		assertTrue(out.toString().contains("Param2"));
		assertTrue(out.toString().contains("HTML"));
		assertTrue(out.toString().contains("Servlet-Name"));
		assertTrue(out.toString().contains("Root"));
		assertTrue(out.toString().contains("Servlet"));
	}
	
	@Test
	public void test4() throws IOException {
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
		
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("aaa"));
		assertTrue(out.toString().contains("nnn"));
		assertTrue(out.toString().contains("ccc"));
		assertTrue(out.toString().contains("1000.21"));
		assertTrue(out.toString().contains("100"));
		assertTrue(out.toString().contains("testList"));
	}

	@Test
	public void test5() throws IOException {
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

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("v11"));
		assertTrue(out.toString().contains("v12"));
		assertTrue(out.toString().contains("v13"));
		assertTrue(out.toString().contains("v21"));
		assertTrue(out.toString().contains("v22"));
		assertTrue(out.toString().contains("v23"));
		assertTrue(out.toString().contains("root"));
		assertTrue(out.toString().contains("root1"));
	}

	@Test
	public void test6() throws IOException {
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
		
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("v11"));
		assertTrue(out.toString().contains("v12"));
		assertTrue(out.toString().contains("v13"));
		assertTrue(out.toString().contains("v21"));
		assertTrue(out.toString().contains("v22"));
		assertTrue(out.toString().contains("v23"));
		assertTrue(out.toString().contains("root1"));
		assertTrue(out.toString().contains("root2"));
		assertTrue(out.toString().contains("root3"));
	}

	@Test
	public void test7() throws IOException {
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		for (int i = 0; i < 1000; i++) {
			p.add(i);
		}

		Collection c = new ArrayList();
		String p1 = "Example";
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

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Example"));
		assertTrue(out.toString().contains("100"));
		assertTrue(out.toString().contains("1000.21"));
		assertTrue(out.toString().contains("nickname"));
		assertTrue(out.toString().contains("anotherlist"));
	}

}
