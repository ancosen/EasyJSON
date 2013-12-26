package com.easyjson.json.tokenizer.common.source;

public interface JSONCharacterSource {
	int nextChar();

	int getIndex();

	void destroy();
}
