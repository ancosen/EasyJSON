package com.easyjson.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Andrea Cosentino<ancosen@gmail.com>
 *
 */

import junit.framework.TestCase;

public class EncondingTest extends TestCase {

	public void test1() throws IOException {
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		map.put("Id", 29);
		map.put("type","MIME");
		JSONEncoder.writeJSONString(map, out);
		System.out.println(out);
	}
	
	public void test2() throws IOException {
		StringWriter out = new StringWriter();
		List p = new ArrayList();
		p.add("Ciao");
		p.add(22.12);
		p.add(-26.19);
		List p1 = new ArrayList();
		p1.add("Hello");
		p1.add("Idiot");
		p1.add("Stupid");
		Map map = new HashMap();
		map.put("Id", 29);
		map.put("List1", p);
		map.put("List2", p1);
		map.put("type","MIME");
		JSONEncoder.writeJSONString(map, out);
		System.out.println(out);
	}
	
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
		
		JSONEncoder.writeJSONString(map, out);
		System.out.println(out);
	}
	
	public void testLinkedHashMap() throws IOException{
		StringWriter out = new StringWriter();
		Map map = new LinkedHashMap();
		map.put("userName", "Peppe");
		map.put("ID", new Integer(12));
		JSONEncoder.writeJSONString(map, out);
		System.out.println(out);
	}
	
	public void testNull() throws IOException{
		StringWriter out = new StringWriter();
		Map map = null;
		JSONEncoder.writeJSONString(map, out);
		System.out.println(out);
	}

}
