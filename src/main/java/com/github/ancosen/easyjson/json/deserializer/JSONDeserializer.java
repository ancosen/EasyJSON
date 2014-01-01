/*******************************************************************************
* Copyright (c) 2013 EclipseSource.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Ralf Sternberg - initial implementation and API
******************************************************************************/

package com.github.ancosen.easyjson.json.deserializer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.ancosen.easyjson.json.deserializer.operation.IJSONDecodingOperation;

/*
* | bufferOffset
* v
* [a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t] < input
* [l|m|n|o|p|q|r|s|t|?|?] < buffer
* ^ ^
* | index fill
*/

public class JSONDeserializer implements IJSONDecodingOperation{

	private static final int MIN_BUFFER_SIZE = 10;
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	private final Reader reader;
	private final char[] buffer;
	private int bufferOffset;
	private int index;
	private int fill;
	private int line;
	private int lineOffset;
	private int current;
	private StringBuilder captureBuffer;
	private int captureStart;

	public JSONDeserializer(String string) {
		this(new StringReader(string), Math.max(MIN_BUFFER_SIZE,
				Math.min(DEFAULT_BUFFER_SIZE, string.length())));
	}

	public JSONDeserializer(Reader reader) {
		this(reader, DEFAULT_BUFFER_SIZE);
	}

	public JSONDeserializer(Reader reader, int buffersize) {
		this.reader = reader;
		buffer = new char[buffersize];
		line = 1;
		captureStart = -1;
	}

	public Object parse() throws IOException, ParseException {
		read();
		skipWhiteSpace();
		Object result = readValue();
		skipWhiteSpace();
		if (!isEndOfText()) {
			throw error("Unexpected character");
		}
		return result;
	}

	private Object readValue() throws IOException, ParseException {
		switch (current) {
		case 'n':
			return readNull();
		case 't':
			return readTrue();
		case 'f':
			return readFalse();
		case '"':
			return readString();
		case '[':
			return readArray();
		case '{':
			return readObject();
		case '-':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return readNumber();
		default:
			throw expected("value");
		}
	}
	
	private List readArray() throws IOException, ParseException {
		read();
		List array = new ArrayList();
		skipWhiteSpace();
		if (readChar(']')) {
			return array;
		}
		do {
			skipWhiteSpace();
			array.add(readValue());
			skipWhiteSpace();
		} while (readChar(','));
		if (!readChar(']')) {
			throw expected("',' or ']'");
		}
		return array;
	}

	private Map readObject() throws IOException, ParseException {
		read();
		LinkedHashMap object = new LinkedHashMap();
		skipWhiteSpace();
		if (readChar('}')) {
			return object;
		}
		do {
			skipWhiteSpace();
			String name = readName();
			skipWhiteSpace();
			if (!readChar(':')) {
				throw expected("':'");
			}
			skipWhiteSpace();
			object.put(name, readValue());
			skipWhiteSpace();
		} while (readChar(','));
		if (!readChar('}')) {
			throw expected("',' or '}'");
		}
		return object;
	}

	private String readName() throws IOException, ParseException {
		if (current != '"') {
			throw expected("name");
		}
		return readStringInternal();
	}

	private Object readNull() throws IOException, ParseException {
		read();
		readRequiredChar('u');
		readRequiredChar('l');
		readRequiredChar('l');
		return null;
	}

	private Boolean readTrue() throws IOException, ParseException {
		read();
		readRequiredChar('r');
		readRequiredChar('u');
		readRequiredChar('e');
		return Boolean.TRUE;
	}

	private Boolean readFalse() throws IOException, ParseException {
		read();
		readRequiredChar('a');
		readRequiredChar('l');
		readRequiredChar('s');
		readRequiredChar('e');
		return Boolean.FALSE;
	}

	private void readRequiredChar(char ch) throws IOException, ParseException {
		if (!readChar(ch)) {
			throw expected("'" + ch + "'");
		}
	}

	private String readString() throws IOException, ParseException {
		return new String(readStringInternal());
	}

	private String readStringInternal() throws IOException, ParseException {
		read();
		startCapture();
		while (current != '"') {
			if (current == '\\') {
				pauseCapture();
				readEscape();
				startCapture();
			} else if (current < 0x20) {
				throw expected("valid string character");
			} else {
				read();
			}
		}
		String string = endCapture();
		read();
		return string;
	}

	private void readEscape() throws IOException, ParseException {
		read();
		switch (current) {
		case '"':
		case '/':
		case '\\':
			captureBuffer.append((char) current);
			break;
		case 'b':
			captureBuffer.append('\b');
			break;
		case 'f':
			captureBuffer.append('\f');
			break;
		case 'n':
			captureBuffer.append('\n');
			break;
		case 'r':
			captureBuffer.append('\r');
			break;
		case 't':
			captureBuffer.append('\t');
			break;
		case 'u':
			char[] hexChars = new char[4];
			for (int i = 0; i < 4; i++) {
				read();
				if (!isHexDigit()) {
					throw expected("hexadecimal digit");
				}
				hexChars[i] = (char) current;
			}
			captureBuffer.append((char) Integer.parseInt(
					String.valueOf(hexChars), 16));
			break;
		default:
			throw expected("valid escape sequence");
		}
		read();
	}

	private Long readNumber() throws IOException, ParseException {
		startCapture();
		readChar('-');
		int firstDigit = current;
		if (!readDigit()) {
			throw expected("digit");
		}
		if (firstDigit != '0') {
			while (readDigit()) {
			}
		}
		readFraction();
		readExponent();
		return new Long(endCapture());
	}

	private boolean readFraction() throws IOException, ParseException {
		if (!readChar('.')) {
			return false;
		}
		if (!readDigit()) {
			throw expected("digit");
		}
		while (readDigit()) {
		}
		return true;
	}

	private boolean readExponent() throws IOException, ParseException {
		if (!readChar('e') && !readChar('E')) {
			return false;
		}
		if (!readChar('+')) {
			readChar('-');
		}
		if (!readDigit()) {
			throw expected("digit");
		}
		while (readDigit()) {
		}
		return true;
	}

	private boolean readChar(char ch) throws IOException, ParseException {
		if (current != ch) {
			return false;
		}
		read();
		return true;
	}

	private boolean readDigit() throws IOException, ParseException {
		if (!isDigit()) {
			return false;
		}
		read();
		return true;
	}

	private void skipWhiteSpace() throws IOException, ParseException {
		while (isWhiteSpace()) {
			read();
		}
	}

	private void read() throws IOException, ParseException {
		if (isEndOfText()) {
			throw error("Unexpected end of input");
		}
		if (index == fill) {
			if (captureStart != -1) {
				captureBuffer.append(buffer, captureStart, fill - captureStart);
				captureStart = 0;
			}
			bufferOffset += fill;
			fill = reader.read(buffer, 0, buffer.length);
			index = 0;
			if (fill == -1) {
				current = -1;
				return;
			}
		}
		if (current == '\n') {
			line++;
			lineOffset = bufferOffset + index;
		}
		current = buffer[index++];
	}

	private void startCapture() {
		if (captureBuffer == null) {
			captureBuffer = new StringBuilder();
		}
		captureStart = index - 1;
	}

	private void pauseCapture() {
		int end = current == -1 ? index : index - 1;
		captureBuffer.append(buffer, captureStart, end - captureStart);
		captureStart = -1;
	}

	private String endCapture() {
		int end = current == -1 ? index : index - 1;
		String captured;
		if (captureBuffer.length() > 0) {
			captureBuffer.append(buffer, captureStart, end - captureStart);
			captured = captureBuffer.toString();
			captureBuffer.setLength(0);
		} else {
			captured = new String(buffer, captureStart, end - captureStart);
		}
		captureStart = -1;
		return captured;
	}

	private ParseException expected(String expected) {
		if (isEndOfText()) {
			return error("Unexpected end of input");
		}
		return error("Expected " + expected);
	}

	private ParseException error(String message) {
		int absIndex = bufferOffset + index;
		int column = absIndex - lineOffset;
		int offset = isEndOfText() ? absIndex : absIndex - 1;
		return new ParseException(message, offset);
	}

	private boolean isWhiteSpace() {
		return current == ' ' || current == '\t' || current == '\n'
				|| current == '\r';
	}

	private boolean isDigit() {
		return current >= '0' && current <= '9';
	}

	private boolean isHexDigit() {
		return current >= '0' && current <= '9' || current >= 'a'
				&& current <= 'f' || current >= 'A' && current <= 'F';
	}

	private boolean isEndOfText() {
		return current == -1;
	}

}
