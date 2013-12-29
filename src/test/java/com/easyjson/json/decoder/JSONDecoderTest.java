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
		JSONEncoder.toJSONString(map, out);
		System.err.println(out.toString());
		System.err.println(map.size());

		JSONTokenizer jsonTokenizer = new JSONTokenizer(out.toString(), true);

		List<Token> tokens = new ArrayList<Token>();
		Token token;
		while ((token = jsonTokenizer.next()).type() != TokenType.END) {
			System.err.println(token.value());
			tokens.add(token);
		}

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
