package com.github.ancosen.easyjson.comparison.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.ancosen.easyjson.json.serializer.JSONSerializer;
import com.github.ancosen.easyjson.json.serializer.mock.ComplexExample;
import com.github.ancosen.easyjson.json.serializer.mock.Example;
import com.google.gson.Gson;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = Parameterized.class)
public class ComparisonTest {

	private int number;

	public ComparisonTest(int number) {
		this.number = number;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 50 }, { 100 }, { 200 }, { 500 },
				{ 1000 }, { 2000 }, { 5000 }, { 10000 } };
		return Arrays.asList(data);
	}

	@Test
	public void testComparison1() throws JsonGenerationException,
			JsonMappingException, IOException {
		System.err.println("----------------------------------------------------");
		System.err.println("Test Comparison 1 with parameter " + number);
		StringWriter out = new StringWriter();

		List add = new ArrayList();
		LinkedHashMap addresses = new LinkedHashMap();
		addresses.put("description", "foo");
		addresses.put("street", "123 kksk");
		addresses.put("city", "Atlanta");
		addresses.put("state", "USA");
		addresses.put("postalcode", "ANANA");
		for (int i = 0; i < number; i++) {
			add.add(addresses);
		}

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

		System.err.println("Map Size: " + map.size());
		System.err.println("Map Keyset Size: " + map.keySet().size());

		ObjectMapper mapper = new ObjectMapper();
		String jsonJackson = "";

		Gson gson = new Gson();
		String jsonGson = "";

		String jsonEasyjson = "";

		long start = System.nanoTime();
		JSONSerializer.toJSONString(map, out);
		jsonEasyjson = out.toString();
		long end = System.nanoTime();
		System.out.println("EasyJSON Encoder toke " + (end - start) / 1000 + " MicroSeconds");

		long startJackson = System.nanoTime();
		jsonJackson = mapper.writeValueAsString(map);
		long endJackson = System.nanoTime();
		System.out.println("Jackson Encoder toke "
				+ (endJackson - startJackson) / 1000 + " MicroSeconds");

		long startGson = System.nanoTime();
		jsonGson = gson.toJson(map);
		long endGson = System.nanoTime();
		System.out.println("Gson Encoder toke " + (endGson - startGson) / 1000 + " MicroSeconds");

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("description"));
		assertEquals(jsonEasyjson, jsonJackson);
		assertEquals(jsonEasyjson, jsonGson);

		System.err.println("----------------------------------------------------");
	}

	@Test
	public void testComparison2() throws JsonGenerationException,
			JsonMappingException, IOException {
		System.err.println("----------------------------------------------------");

		System.err.println("Test Comparison 2 with parameter " + number);
		StringWriter out = new StringWriter();

		List add = new ArrayList();
		LinkedHashMap addresses = new LinkedHashMap();
		addresses.put("description", "foo");
		addresses.put("street", "123 kksk");
		addresses.put("city", "Atlanta");
		addresses.put("state", "USA");
		addresses.put("postalcode", "ANANA");
		for (int i = 0; i < 50; i++) {
			add.add(addresses);
		}

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

		for (int z = 0; z < number; z++) {
			map.put("list" + z, new String("prova"));
		}

		System.err.println("Map Size: " + map.size());
		System.err.println("Map Keyset Size: " + map.keySet().size());

		ObjectMapper mapper = new ObjectMapper();
		String jsonJackson = "";

		Gson gson = new Gson();
		String jsonGson = "";

		String jsonEasyjson = "";

		long start = System.nanoTime();
		JSONSerializer.toJSONString(map, out);
		long end = System.nanoTime();
		System.out.println("EasyJSON Encoder toke " + (end - start) / 1000 + " MicroSeconds");

		long startJackson = System.nanoTime();
		jsonJackson = mapper.writeValueAsString(map);
		long endJackson = System.nanoTime();
		System.out.println("Jackson Encoder toke " + (endJackson - startJackson) / 1000 + " MicroSeconds");

		long startGson = System.nanoTime();
		jsonGson = gson.toJson(map);
		long endGson = System.nanoTime();
		System.out.println("Gson Encoder toke " + (endGson - startGson) / 1000 + " MicroSeconds");

		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertTrue(out.toString().contains("description"));
		assertEquals(out.toString(), jsonJackson);
		assertEquals(out.toString(), jsonGson);

		System.err.println("----------------------------------------------------");
	}
	
	@Test
	public void testComparison3() throws JsonGenerationException, JsonMappingException, IOException{
		System.err.println("----------------------------------------------------");
		System.err.println("Test Comparison 3 with parameter " + number);
		StringWriter out = new StringWriter();
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type","MIME");
		List p = new ArrayList();
		for (int i=0;i<number;i++){
			p.add(new Example(23, "Andrea"));
		}
		map.put("Subject", p);

		System.err.println("Map Size: " + map.size());
		System.err.println("Map Keyset Size: " + map.keySet().size());

		ObjectMapper mapper = new ObjectMapper();
		String jsonJackson = "";

		Gson gson = new Gson();
		String jsonGson = "";

		String jsonEasyjson = "";

		long start = System.nanoTime();
		JSONSerializer.toJSONString(map, out);
		long end = System.nanoTime();
		System.out.println("EasyJSON Encoder toke " + (end - start) / 1000 + " MicroSeconds");

		long startJackson = System.nanoTime();
		jsonJackson = mapper.writeValueAsString(map);
		long endJackson = System.nanoTime();
		System.out.println("Jackson Encoder toke " + (endJackson - startJackson) / 1000 + " MicroSeconds");

		long startGson = System.nanoTime();
		jsonGson = gson.toJson(map);
		long endGson = System.nanoTime();
		System.out.println("Gson Encoder toke " + (endGson - startGson) / 1000 + " MicroSeconds");
		
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertEquals(out.toString(), jsonJackson);
		assertEquals(out.toString(), jsonGson);

		System.err.println("----------------------------------------------------");
	}
	
	@Test
	public void testComparison4() throws JsonGenerationException, JsonMappingException, IOException{
		System.err.println("----------------------------------------------------");
		System.err.println("Test Comparison 4 with parameter " + number);
		StringWriter out = new StringWriter();
		LinkedHashMap map1 = new LinkedHashMap();
		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type","MIME");
		List p = new ArrayList();
		for (int i=0;i<number;i++){
			p.add(new Example(23, "Andrea"));
		}
		map.put("Subjects", p);
		ComplexExample m = new ComplexExample(46, "Company", p, map1);
		map.put("Complex",m);

		System.err.println("Map Size: " + map.size());
		System.err.println("Map Keyset Size: " + map.keySet().size());

		ObjectMapper mapper = new ObjectMapper();
		String jsonJackson = "";

		Gson gson = new Gson();
		String jsonGson = "";

		String jsonEasyjson = "";

		long start = System.nanoTime();
		JSONSerializer.toJSONString(map, out);
		long end = System.nanoTime();
		System.out.println("EasyJSON Encoder toke " + (end - start) / 1000 + " MicroSeconds");

		long startJackson = System.nanoTime();
		jsonJackson = mapper.writeValueAsString(map);
		long endJackson = System.nanoTime();
		System.out.println("Jackson Encoder toke " + (endJackson - startJackson) / 1000 + " MicroSeconds");

		long startGson = System.nanoTime();
		jsonGson = gson.toJson(map);
		long endGson = System.nanoTime();
		System.out.println("Gson Encoder toke " + (endGson - startGson) / 1000 + " MicroSeconds");
		
		assertNotNull(out.toString());
		assertTrue(out.toString().length() > 0);
		assertEquals(out.toString(), jsonJackson);
		assertEquals(out.toString(), jsonGson);

		System.err.println("----------------------------------------------------");
	}
}
