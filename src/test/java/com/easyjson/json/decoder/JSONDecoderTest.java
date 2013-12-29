package com.easyjson.json.decoder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.easyjson.json.encoder.JSONEncoder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JSONDecoderTest {

	@Test
	public void test1() throws IOException, ParseException {
		StringWriter out = new StringWriter();

		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("Peppe", new Long(974));
		ArrayList list = new ArrayList();
		for (int i = 0; i < 20; i++) {
			list.add("Peppeira");
		}
		map.put("list", list);

		JSONEncoder.toJSONString(map, out);

		Object jsonParser = new JsonDecoder(out.toString()).parse();

		assertTrue(equalMaps(map, (Map) jsonParser));

	}

	@Test
	public void test2() throws IOException, ParseException {
		StringWriter out = new StringWriter();

		List add = new ArrayList();
		LinkedHashMap addresses = new LinkedHashMap();
		addresses.put("description", "foo");
		addresses.put("street", "123 kksk");
		addresses.put("city", "Atlanta");
		addresses.put("state", "USA");
		addresses.put("postalcode", "ANANA");
		for (int i = 0; i < 20; i++) {
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
		
		JSONEncoder.toJSONString(map, out);

		Object jsonParser = new JsonDecoder(out.toString()).parse();

		assertTrue(equalMaps(map, (Map) jsonParser));

	}
	
	@Test
	public void test3() throws IOException, ParseException{
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
		
		JSONEncoder.toJSONString(map, out);
		assertNotNull(out.toString());
		
		Object jsonParser = new JsonDecoder(out.toString()).parse();

		assertTrue(equalMaps(map, (Map) jsonParser));

	}

	public boolean equalMaps(Map<?, ?> map1, Map<?, ?> map2) {

		if (map1 == null || map2 == null || map1.size() != map2.size()) {
			return false;
		}

		for (Object key : map1.keySet()) {
			if (!map1.get(key).equals(map2.get(key))) {
				return false;
			}
		}
		return true;
	}

}
