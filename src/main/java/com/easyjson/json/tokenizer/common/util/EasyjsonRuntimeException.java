package com.easyjson.json.tokenizer.common.util;

public class EasyjsonRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public EasyjsonRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EasyjsonRuntimeException(String message)
    {
        super(message);
    }

    public EasyjsonRuntimeException(Throwable cause)
    {
        super(cause);
    }

}

