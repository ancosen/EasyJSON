package com.github.ancosen.easyjson.json.serializer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.github.ancosen.easyjson.json.serializer.mock.Example;
import com.github.ancosen.easyjson.json.serializer.utils.Utils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilsTest {
	
	@Test
	public void Test1(){
		String p = new String("Prova");
		assertTrue(Utils.isJDKClass(p));
	}
	
	@Test
	public void Test2(){
		Map p = new HashMap();
		assertTrue(Utils.isJDKClass(p));
	}
	
	@Test
	public void Test3(){
		List p = new ArrayList();
		assertTrue(Utils.isJDKClass(p));
	}
	
	@Test
	public void Test4(){
		Example p = new Example(23, "peppone");
		assertFalse(Utils.isJDKClass(p));
	}
}
