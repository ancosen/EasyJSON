package com.easyjson.json.decoder.parser;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.easyjson.json.tokenizer.common.token.Token;
import com.easyjson.json.tokenizer.common.token.TokenType;

public class ParseToken implements IParseToken{

	private Logger _log;
	
	private List _tokens;
	
	private LinkedHashMap _map;
	
	public ParseToken(List aTokensList, LinkedHashMap aMap, Logger aLogger){
		_log = aLogger;
		_tokens = aTokensList;
		_map = aMap;
	}

	@Override
	public void parse() {
		int position = 0;
		for (int i = 0; i<_tokens.size(); i++){
			Token token = (Token) _tokens.get(i);
			switch (token.type()){
			case BRACKET_OPEN:
				ArrayList sublist = new ArrayList();
				StringWriter key = new StringWriter();
				position = build(sublist, key, i);
				if (sublist.size() > 1) _map.put(key.toString(),sublist);
				else _map.put(key.toString(),sublist.get(0));
				i=position;
				break;
			default: 
				break;
			}
				
		}
		
	}
	
	public int build(List sublist, StringWriter key, int position) {
		position = position + 1;
		Token token = (Token) _tokens.get(position);
		key.append((CharSequence) token.value());
		position++;
		for (;position<_tokens.size();position++){
			while (!TokenType.BRACKET_CLOSE.equals(token.type())){
				token = (Token) _tokens.get(position);
				switch (token.type()){
					case LONG:
						sublist.add((Long)token.value());
						position++;
						break;
					case INTEGER:
						sublist.add((Integer) token.value());
						position++;
						break;
					case STRING:
						sublist.add((String) token.value());
						position++;
						break;
					case DECIMAL:
						sublist.add((BigDecimal) token.value());
						position++;
						break;
					case TRUE:
						sublist.add((Boolean) token.value());
						position++;
						break;
					case FALSE:
						sublist.add((Boolean) token.value());
						position++;
						break;
					case NULL:
						sublist.add(null);
						position++;
						break;
					default:
						position++;
						break;
				}
			}
			break;
		}
		return position;
		
	}

}
