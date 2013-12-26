package com.easyjson.json.tokenizer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.easyjson.json.encoder.JSONEncoder;
import com.easyjson.json.tokenizer.common.token.Token;
import com.easyjson.json.tokenizer.common.token.TokenType;

public class JSONTokenizerTest extends TestCase {

	public void test1() throws IOException {

		StringWriter out = new StringWriter();
		List p = new ArrayList();
		p.add("Ciao");
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
		map.put("type", "MIME");
		JSONEncoder.toJSONString(map, out);
		
		List<Token> tokens = new ArrayList<Token>();
		JSONTokenizer tokenizer = new JSONTokenizer(out.toString(), true);
		Token token;

		while ((token = tokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}

	}
	
	public void test2() throws IOException{
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
		
		List<Token> tokens = new ArrayList<Token>();
		JSONTokenizer tokenizer = new JSONTokenizer(out.toString(), true);
		Token token;

		while ((token = tokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}
		
		assertTrue(tokens.size() > 0);
	}
	
	public void test3() throws IOException{
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
		
		List<Token> tokens = new ArrayList<Token>();
		JSONTokenizer tokenizer = new JSONTokenizer(out.toString(), true);
		Token token;

		while ((token = tokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}
		
		assertTrue(tokens.size() > 0);
	}
}
