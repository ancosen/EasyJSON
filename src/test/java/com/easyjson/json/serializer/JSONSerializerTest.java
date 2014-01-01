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
package com.easyjson.json.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JSONSerializerTest{

	@Test
	public void testNull(){
		StringWriter out = new StringWriter();
		LinkedHashMap map = null;
		JSONSerializer.toJSONString(map, out);
		assertNotNull(out.toString());
	}
	
	@Test	
	public void testEmpty(){
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		JSONSerializer.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().equals("{}"));
	}
	
	@Test	
	public void test1() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type","MIME");
		JSONSerializer.toJSONString(map, out);
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Id"));
		assertTrue(out.toString().contains("29"));
		assertTrue(out.toString().contains("type"));
		assertTrue(out.toString().contains("MIME"));
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile1.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile1.json"), "utf-8"), out.toString());
	}

	@Test
	public void test2() throws IOException{
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		p.add("Start");
		p.add(22.12);
		p.add(-26.19);
		List p1 = new ArrayList();
		p1.add("Hello");
		p1.add("Ciao");
		p1.add("Salut");
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("List1", p);
		map.put("List2", p1);
		map.put("type","MIME");
		JSONSerializer.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Start"));
		assertTrue(out.toString().contains("22.12"));
		assertTrue(out.toString().contains("-26.19"));
		assertTrue(out.toString().contains("Hello"));
		assertTrue(out.toString().contains("Ciao"));
		assertTrue(out.toString().contains("Salut"));
		assertTrue(out.toString().contains("List1"));
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile2.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile2.json"), "utf-8"), out.toString());
	}

	@Test
	public void test3() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		LinkedHashMap map1 = new LinkedHashMap();
		LinkedHashMap map2 = new LinkedHashMap();
		LinkedHashMap map3 = new LinkedHashMap();
		map3.put("Param1", "a.html");
		map3.put("Param2", "b.html");
		map3.put("HTML Page", "HTML");
		map2.put("HTML", map3);
		map2.put("Param1", "a");
		map2.put("Param2", "b");
		map2.put("Servlet-Name", "ServletExe");
		map1.put("Id", new Long(29));
		map1.put("Servlet", map2);
		map.put("Root",map1);
		
		JSONSerializer.toJSONString(map, out);
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Param1"));
		assertTrue(out.toString().contains("Param2"));
		assertTrue(out.toString().contains("HTML"));
		assertTrue(out.toString().contains("Servlet-Name"));
		assertTrue(out.toString().contains("Root"));
		assertTrue(out.toString().contains("Servlet"));

		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile3.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile3.json"), "utf-8"), out.toString());
	}
	
	@Test
	public void test4() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap obj = new LinkedHashMap();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new BigDecimal(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		LinkedList list = new LinkedList();
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		list2.add(new Long(0));
		list2.add(new Long(1));
		list2.add(new Long(2));
		list1.add("aaa");
		list1.add("nnn");
		list1.add("ccc");
		list1.add(list2);
		list.add(list1);
		list.add("foo");
		list.add(new Long(29));
		list.add(new BigDecimal(1000.21));
		list.add(new Boolean(true));
		list.add(null);
		obj.put("testList", list);
		JSONSerializer.toJSONString(obj, out);
		
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("aaa"));
		assertTrue(out.toString().contains("nnn"));
		assertTrue(out.toString().contains("ccc"));
		assertTrue(out.toString().contains("1000.21"));
		assertTrue(out.toString().contains("100"));
		assertTrue(out.toString().contains("testList"));

		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile4.json"),"UTF-8");
		JSONSerializer.toJSONString(obj, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile4.json"), "utf-8"), out.toString());
	}

	@Test
	public void test5() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap m1 = new LinkedHashMap();
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
		JSONSerializer.toJSONString(m1, out);

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
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile5.json"),"UTF-8");
		JSONSerializer.toJSONString(m1, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile5.json"), "utf-8"), out.toString());
	}

	@Test
	public void test6() throws IOException {
		StringWriter out = new StringWriter();
		LinkedHashMap m1 = new LinkedHashMap();
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
		JSONSerializer.toJSONString(m1, out);
		
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

		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile6.json"),"UTF-8");
		JSONSerializer.toJSONString(m1, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile6.json"), "utf-8"), out.toString());
	}

	@Test
	public void test7() throws IOException {
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		for (int i = 0; i < 10; i++) {
			p.add(new Long(i));
		}

		Collection c = new ArrayList();
		String p1 = "Example";
		for (int f = 0; f < 5; f++) {
			c.add(p1);
		}

		Map obj1 = new HashMap();
		obj1.put("name", "foo");
		obj1.put("num", new Long(100));
		obj1.put("balance", new BigDecimal(1000.21));
		obj1.put("list", p);

		LinkedHashMap obj2 = new LinkedHashMap();
		obj2.put("is_vip", new Boolean(true));
		obj2.put("nickname", null);
		obj2.put("anotherlist", c);
		obj2.putAll(obj1);

		JSONSerializer.toJSONString(obj2, out);

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Example"));
		assertTrue(out.toString().contains("100"));
		assertTrue(out.toString().contains("1000.21"));
		assertTrue(out.toString().contains("nickname"));
		assertTrue(out.toString().contains("anotherlist"));

		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile7.json"),"UTF-8");
		JSONSerializer.toJSONString(obj2, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile7.json"), "utf-8"), out.toString());
	}
	
	@Test
	public void test8() throws JsonGenerationException, JsonMappingException, IOException{
		StringWriter out = new StringWriter();
		
		List add = new ArrayList();
		LinkedHashMap addresses = new LinkedHashMap();
		addresses.put("description", "foo");
		addresses.put("street", "123 kksk");
		addresses.put("city", "Atlanta");
		addresses.put("state", "USA");
		addresses.put("postalcode", "ANANA");
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		add.add(addresses);
		
		LinkedHashMap mapSpouse = new LinkedHashMap();
		mapSpouse.put("name", "Peppe");
		mapSpouse.put("Surname", "Pig");
		mapSpouse.put("addresses", addresses);
		mapSpouse.put("addresses1", addresses);
		mapSpouse.put("addresses2", addresses);
		mapSpouse.put("addresses3", addresses);
		mapSpouse.put("addresses4", addresses);
		mapSpouse.put("addresses5", addresses);
		mapSpouse.put("addresses6", addresses);
		mapSpouse.put("addresses7", addresses);
		mapSpouse.put("addresses8", addresses);
		mapSpouse.put("addresses9", addresses);
		mapSpouse.put("addresses10", addresses);
		mapSpouse.put("addresses11", addresses);
		mapSpouse.put("addresses12", addresses);
		mapSpouse.put("addresses13", addresses);
		mapSpouse.put("addresses14", addresses);
		mapSpouse.put("addresses15", addresses);
		
		LinkedHashMap map = new LinkedHashMap();
		map.put("name", "Peppa");
		map.put("surname", "Pig");
		map.put("birthdate", new Date().toString());
		map.put("spouse", mapSpouse);
		map.put("addresses", add);
		map.put("addresses1", add);
		map.put("addresses2", add);
		map.put("addresses3", add);
		map.put("addresses4", add);
		map.put("addresses5", add);
		map.put("addresses6", add);
		map.put("addresses7", add);
		map.put("addresses8", add);
		map.put("addresses9", add);
		map.put("addresses10", add);
		map.put("addresses11", add);
		map.put("addresses12", add);
		map.put("addresses13", add);
		map.put("addresses14", add);
		map.put("addresses15", add);
	
		JSONSerializer.toJSONString(map, out);

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("description"));
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile8.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile8.json"), "utf-8"), out.toString());
	}

}
