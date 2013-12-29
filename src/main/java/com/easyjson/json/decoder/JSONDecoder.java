package com.easyjson.json.decoder;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easyjson.json.decoder.operation.IJSONDecodingOperation;
import com.easyjson.json.decoder.parser.ParseToken;

public class JSONDecoder implements IJSONDecodingOperation{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(JSONDecoder.class);

	public JSONDecoder() {
		super();
	}

	public static void toMap(List tokens, LinkedHashMap map) {		
        log.info("Start Creating JSON Map");
        
        if (tokens.size() == 0){
        	return;
        }
        
        new ParseToken(tokens,map,log).parse();

		return;
	}
}
