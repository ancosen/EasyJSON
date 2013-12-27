package com.easyjson.json.tokenizer.common.token;

import java.math.BigDecimal;

public enum TokenType
{
    BRACE_OPEN("{"),
    BRACE_CLOSE("}"),
    COLON(":"),
    BRACKET_OPEN("["),
    BRACKET_CLOSE("]"),
    COMMA(","),
    STRING(String.class),
    LONG(Long.class),
    INTEGER(Integer.class),
    DECIMAL(BigDecimal.class),
    TRUE(Boolean.TRUE),
    FALSE(Boolean.FALSE),
    NULL(null),
    END(null);

    private Object validContent;

    private TokenType(Object validContent)
    {
        this.validContent = validContent;
    }

    public void checkValue(Object value) throws IllegalArgumentException
    {
        if (isClassRestricted())
        {
            Class cls = (Class)validContent;
            if ( !cls.isAssignableFrom(value.getClass()))
            {
                throw new IllegalArgumentException("Values for "+this.name()+" must be a "+cls);
            }
        }
        else
        {
            if (validContent == null)
            {
                if (value != null)
                {
                    throw new IllegalArgumentException("Only null values allowed for "+this.name());
                }
            }
            else
            {
                if (!validContent.equals(value))
                {
                    throw new IllegalArgumentException("Value for "+this.name()+" must be "+validContent);
                }
            }
        }
    }

    public boolean isClassRestricted()
    {
        return validContent instanceof Class;
    }

    public Object getValidContent()
    {
        return validContent;
    }

    public boolean isPrimitive()
    {
        switch(this)
        {
            case STRING:
            case TRUE:
            case FALSE:
            case NULL:
            case LONG:
            case DECIMAL:
                return true;
            default:
                return false;
        }
    }
}

