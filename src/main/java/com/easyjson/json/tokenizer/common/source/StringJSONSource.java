package com.easyjson.json.tokenizer.common.source;

public class StringJSONSource implements JSONCharacterSource {
	
	private String json;

	private int index;

	private int length;

	public StringJSONSource(String json) {
		this.json = json;
		this.length = json.length();
	}

	public int nextChar() {
		if (index < length) {
			return json.charAt(index++);
		} else {
			return -1;
		}
	}

	public int getIndex() {
		return index;
	}

	public void destroy() {
		
	}
}
