package com.easyjson.json.tokenizer.common.util;

public class JSONParseException extends EasyjsonRuntimeException {
	private static final long serialVersionUID = 2916869311842277595L;

	public JSONParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public JSONParseException(String message) {
		super(message);
	}
}
