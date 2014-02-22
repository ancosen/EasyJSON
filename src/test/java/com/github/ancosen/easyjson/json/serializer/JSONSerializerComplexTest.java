package com.github.ancosen.easyjson.json.serializer;

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
import org.junit.Test;

import com.github.ancosen.easyjson.json.serializer.mock.ComplexExample;
import com.github.ancosen.easyjson.json.serializer.mock.Example;
import com.github.ancosen.easyjson.json.serializer.mock.Group;
import com.github.ancosen.easyjson.json.serializer.mock.Person;

public class JSONSerializerComplexTest {
	
	@Test	
	public void test1() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type","MIME");
		List p = new ArrayList();
		p.add(new Example(23, "Andrea"));
		p.add(new Example(23, "Andrea"));
		p.add(new Example(23, "Andrea"));
		map.put("Subject", p);
		JSONSerializer.toJSONString(map, out);
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Id"));
		assertTrue(out.toString().contains("29"));
		assertTrue(out.toString().contains("type"));
		assertTrue(out.toString().contains("MIME"));
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile10.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile10.json"), "utf-8"), out.toString());
	}
	
	
	@Test	
	public void test2() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map1 = new LinkedHashMap();
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type","MIME");
		List p = new ArrayList();
		for(int i=0;i<10000;i++){
		p.add(new Example(23, "Andrea"));
		}
		map.put("Subjects", p);
		map1.put("person", new String("Andrea"));
		ComplexExample m = new ComplexExample(46, "Company", p, map1);
		map.put("Complex",m);
		JSONSerializer.toJSONString(map, out);
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("Id"));
		assertTrue(out.toString().contains("29"));
		assertTrue(out.toString().contains("type"));
		assertTrue(out.toString().contains("MIME"));
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile11.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile11.json"), "utf-8"), out.toString());
	}
	
	@Test	
	public void test3() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		Person p = new Person("Andrew","Dominik","an838jhahshd23", new Date());
		
		List array = new ArrayList();
		
		for (int i=0;i<2;i++){
			array.add(p);
		}
		
		Group r = new Group("jboss", array);
		
		map.put("world", r);
		JSONSerializer.toJSONString(map, out);
		assertTrue(out.toString().length() > 0);
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile12.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile12.json"), "utf-8"), out.toString());
	}
	
}
