package com.easyjson.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class EncondingTest extends TestCase {

	public void test1() throws IOException {
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		map.put("Id", 29);
		map.put("type","MIME");
		JSONObject.writeJSONString(map, out);
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
		JSONObject.writeJSONString(map, out);
		System.out.println(out);
	}
	
	public void test3() throws IOException{
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		Map map2 = new HashMap();
		map2.put("Id", 23);
		map2.put("prova", 90000);
		Map map1 = new HashMap();
		map1.put("Id", 24);
		map1.put("prova", 90001);
		map.put("Map2", map2);
		map2.put("Map3", map1);
		map.put("Id", 29);
		JSONObject.writeJSONString(map, out);
		System.out.println(out);
	}

}
