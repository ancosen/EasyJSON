package com.easyjson.json.deserializer.operation;

import java.io.IOException;
import java.text.ParseException;

public interface IJSONDecodingOperation {

	public Object parse() throws IOException, ParseException;
}
