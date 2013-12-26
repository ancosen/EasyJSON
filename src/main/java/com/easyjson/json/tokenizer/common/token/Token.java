package com.easyjson.json.tokenizer.common.token;

import java.util.Arrays;

import com.easyjson.json.tokenizer.common.util.JSONParseException;
import com.easyjson.json.tokenizer.common.util.Util;

public class Token
{
    private Object value;

    private TokenType type;

    private final static Token[] SINGLETON_TOKENS = new Token[TokenType.values().length];
    static
    {
        for (TokenType type : TokenType.values())
        {
            if (!type.isClassRestricted())
            {
                SINGLETON_TOKENS[type.ordinal()] = new Token(type, type.getValidContent());
            }
        }
    }

    public static Token getToken(TokenType type)
    {
        return getToken(type, null);
    }
   
    public static Token getToken(TokenType type, Object value)
    {
        if (type.isClassRestricted())
        {
            return new Token(type, value);
        }
        else
        {
            return SINGLETON_TOKENS[type.ordinal()];
        }
    }
   
    private Token(TokenType type, Object value) throws IllegalArgumentException
    {
        if (type == null)
        {
            throw new IllegalArgumentException("type must be given");
        }

        //type.checkValue(value);

        this.type = type;
        this.value = value;
    }

    public final Object value()
    {
        return value;
    }

    public final TokenType type()
    {
        return type;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Token)
        {
            Token that = (Token)obj;

            return this.type == that.type && Util.equals(this.value, that.value);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return 37 + 17 * type.hashCode() + 17 * Util.safeHashcode(value);
    }

    public boolean isType(TokenType type)
    {
        return this.type == type;
    }

    public void expect(TokenType... types)
    {
        for (TokenType type : types)
        {
            if (this.type() == type)
            {
                return;
            }
        }
        throw new JSONParseException("Token "+this+" is not of one of the expected types "+Arrays.asList(types));
    }

    @Override
    public String toString()
    {
        return super.toString()+": "+type+" "+value;
    }

}
