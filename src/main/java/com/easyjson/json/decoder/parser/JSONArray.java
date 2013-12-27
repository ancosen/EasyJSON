package com.easyjson.json.decoder.parser;

import java.util.List;

import com.easyjson.json.tokenizer.common.token.Token;
import com.easyjson.json.tokenizer.common.token.TokenType;

public class JSONArray {
	
	private List _sublist;
	private List _tokens;
	private String _key;
	private int _position;	

	public JSONArray(List aSublist, List aTokensList, String aKey, int aPosition) {
		super();
		this._sublist = aSublist;
		this._tokens = aTokensList;
		this._key = aKey;
		this._position = aPosition;
	}

	public void build() {
		boolean isArray = false;
		_position = _position + 1;
		int i = _position;
		System.err.println(_tokens.size());
		Token token = (Token) _tokens.get(i);
		_key = (String) token.value();
		i++;
		for (;i<_tokens.size();i++){
			while (!TokenType.BRACKET_CLOSE.equals(token.type())){
				token = (Token) _tokens.get(i);
				switch (token.type()){
					case LONG:
						_sublist.add(token.value());
						i++;
						break;
					case STRING:
						_sublist.add(token.value());
						i++;
						break;
					case DECIMAL:
						_sublist.add(token.value());
						i++;
						break;
					case TRUE:
						_sublist.add(token.value());
						i++;
						break;
					case FALSE:
						_sublist.add(token.value());
						i++;
						break;
					case NULL:
						_sublist.add(null);
						i++;
						break;
					default:
						i++;
						break;
				}
			}
			_position = i + 1;
		}
		
	}
}
