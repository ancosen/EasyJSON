package com.github.ancosen.easyjson;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.github.ancosen.easyjson.comparison.serializer.ComparisonTest;
import com.github.ancosen.easyjson.json.deserializer.JSONDeserializerTest;
import com.github.ancosen.easyjson.json.serializer.JSONSerializerComplexTest;
import com.github.ancosen.easyjson.json.serializer.JSONSerializerSimpleTest;
import com.github.ancosen.easyjson.json.serializer.UtilsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	JSONSerializerSimpleTest.class,
	JSONSerializerComplexTest.class, 
	JSONDeserializerTest.class,
	ComparisonTest.class, 
	UtilsTest.class })
public class AllTests {

	@BeforeClass
	public static void init() {
		System.out.println("Start all Tests");
	}

	@AfterClass
	public static void end() {
		System.out.println("End all Tests");
	}
}