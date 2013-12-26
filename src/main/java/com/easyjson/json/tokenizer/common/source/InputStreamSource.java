package com.easyjson.json.tokenizer.common.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.easyjson.json.tokenizer.common.util.ExceptionWrapper;

public class InputStreamSource implements JSONCharacterSource {
	private Reader reader;

	private int index;

	private boolean close;

	public InputStreamSource(InputStream inputStream, boolean close) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(inputStream,
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw ExceptionWrapper.wrap(e);
		}
		this.close = close;
	}

	public int getIndex() {
		return index;
	}

	public int nextChar() {
		try {
			int result = reader.read();
			index++;
			return result;
		} catch (IOException e) {
			throw ExceptionWrapper.wrap(e);
		}
	}

	public void destroy() {
		if (close) {
			try {
				reader.close();
			} catch (IOException e) {
				throw ExceptionWrapper.wrap(e);
			}
		}
	}
}
