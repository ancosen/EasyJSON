package com.easyjson.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.easyjson.json.JSONObject;

public class AppTest extends TestCase {

	public void test1() throws IOException {
		StringWriter out = new StringWriter();
		Map map = new HashMap();
		JSONObject.writeJSONString(map, out);
		System.out.println(out);
	}
}
