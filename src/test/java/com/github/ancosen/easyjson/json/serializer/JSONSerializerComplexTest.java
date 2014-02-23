package com.github.ancosen.easyjson.json.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.github.ancosen.easyjson.json.serializer.mock.ComplexExample;
import com.github.ancosen.easyjson.json.serializer.mock.Example;
import com.github.ancosen.easyjson.json.serializer.mock.Group;
import com.github.ancosen.easyjson.json.serializer.mock.PersonSimple;
import com.github.ancosen.easyjson.json.serializer.mock.addressbook.Address;
import com.github.ancosen.easyjson.json.serializer.mock.addressbook.Person;
import com.github.ancosen.easyjson.json.serializer.mock.addressbook.PhoneNumber;

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
		PersonSimple p = new PersonSimple("Andrew","Dominik","an838jhahshd23", new Date());
		
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
	
	@Test	
	public void test4() throws IOException{
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		
		Address address = new Address();
		address.city = "Pippo";
		address.postalCode = 10100;
		address.state = "NY";
		address.streetAddress = "Street 2nd";
		
		PhoneNumber cell = new PhoneNumber();
		cell.setNumber("333938283");
		cell.setType("mobile");
		
		PhoneNumber home = new PhoneNumber();
		home.setNumber("065757473");
		home.setType("home");
		
		List phoneNumberList = new ArrayList();
		
		phoneNumberList.add(cell);
		phoneNumberList.add(home);
		
		Person p = new Person();
		p.setAddress(address);
		p.setAge(25);
		p.setName("Andrew");
		p.setSurname("Dominik");
		p.setPhoneNumber(phoneNumberList);
		
		map.put("person1", p);
			
		JSONSerializer.toJSONString(map, out);
		assertTrue(out.toString().length() > 0);
		
		OutputStreamWriter fileout = new OutputStreamWriter(new FileOutputStream("./target/testFile13.json"),"UTF-8");
		JSONSerializer.toJSONString(map, fileout);
		
		assertEquals(FileUtils.readFileToString(new File("./target/testFile13.json"), "utf-8"), out.toString());
	}
	
}
