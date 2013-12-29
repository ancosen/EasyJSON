package com.easyjson.json.decoder.parser;

import java.util.LinkedHashMap;

import org.slf4j.Logger;

import com.easyjson.json.tokenizer.JSONTokenizer;

public class ParseToken implements IParseToken {

	private Logger _log;

	private JSONTokenizer _tokens;

	private LinkedHashMap _map;

	public ParseToken(JSONTokenizer aJsonTokenizer, LinkedHashMap aMap,
			Logger aLogger) {
		_log = aLogger;
		_tokens = aJsonTokenizer;
		_map = aMap;
	}

	@Override
	public void parse() {

	}

}
