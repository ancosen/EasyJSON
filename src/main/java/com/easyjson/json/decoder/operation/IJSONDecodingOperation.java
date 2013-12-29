package com.easyjson.json.decoder.operation;

import java.io.IOException;
import java.text.ParseException;

public interface IJSONDecodingOperation {

	public Object parse() throws IOException, ParseException;
}
