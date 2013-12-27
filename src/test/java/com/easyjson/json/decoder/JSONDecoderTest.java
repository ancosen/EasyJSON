package com.easyjson.json.decoder;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.easyjson.json.encoder.JSONEncoder;
import com.easyjson.json.tokenizer.JSONTokenizer;
import com.easyjson.json.tokenizer.common.token.Token;
import com.easyjson.json.tokenizer.common.token.TokenType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JSONDecoderTest {

	@Test
	public void test1() throws IOException {
		StringWriter out = new StringWriter();

		LinkedHashMap map = new LinkedHashMap();
		map.put("Id", new Long(29));
		map.put("type", "MIME");
		JSONEncoder.toJSONString(map, out);

		JSONTokenizer jsonTokenizer = new JSONTokenizer(out.toString(), true);

		List<Token> tokens = new ArrayList<Token>();
		Token token;
		while ((token = jsonTokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}

		LinkedHashMap nuova = new LinkedHashMap();
		JSONDecoder.toMap(tokens, nuova);

		assertTrue(equalMaps(nuova, map));

	}

	@Test
	public void test2() throws IOException {
		StringWriter out = new StringWriter();

		List p = new ArrayList();
		for (int i = 0; i < 2; i++) {
			p.add(new Long(i));
		}

		Collection c = new ArrayList();
		String p1 = "Example";
		for (int f = 0; f < 3; f++) {
			c.add(p1);
		}

		LinkedHashMap obj1 = new LinkedHashMap();
		obj1.put("name", "foo");
		obj1.put("num", new Long(100));
		obj1.put("balance", new BigDecimal(1000.21));
		obj1.put("list", p);

		LinkedHashMap obj2 = new LinkedHashMap();
		obj2.put("is_vip", new Boolean(true));
		obj2.put("nickname", "peppone");
		obj2.put("anotherlist", c);
		obj2.putAll(obj1);

		JSONEncoder.toJSONString(obj2, out);

		JSONTokenizer jsonTokenizer = new JSONTokenizer(out.toString(), true);

		List<Token> tokens = new ArrayList<Token>();
		Token token;
		while ((token = jsonTokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}

		LinkedHashMap nuova = new LinkedHashMap();
		JSONDecoder.toMap(tokens, nuova);

		assertTrue(equalMaps(obj2, nuova));

	}
	
	@Test
	public void test3() throws IOException {
		StringWriter out = new StringWriter();

		List p = new ArrayList();
		for (int i = 0; i < 2; i++) {
			p.add(new Long(i));
		}

		Collection c = new ArrayList();
		String p1 = "Example";
		for (int f = 0; f < 3; f++) {
			c.add(p1);
		}

		LinkedHashMap obj1 = new LinkedHashMap();
		obj1.put("name", "foo");
		obj1.put("num", new Long(100));
		obj1.put("balance", new BigDecimal(1000.21));
		obj1.put("balance3", new BigDecimal(1000.21));
		obj1.put("list", p);

		LinkedHashMap obj2 = new LinkedHashMap();
		obj2.put("is_vip", new Boolean(true));
		obj2.put("nickname", "peppone");
		obj2.put("anotherlist", c);
		obj2.putAll(obj1);

		JSONEncoder.toJSONString(obj2, out);

		JSONTokenizer jsonTokenizer = new JSONTokenizer(out.toString(), true);

		List<Token> tokens = new ArrayList<Token>();
		Token token;
		while ((token = jsonTokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}

		LinkedHashMap nuova = new LinkedHashMap();
		JSONDecoder.toMap(tokens, nuova);

		assertTrue(equalMaps(obj2, nuova));

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
