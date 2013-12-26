package com.easyjson.json.tokenizer.common.util;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;


public class ExceptionWrapper
{
    public static EasyjsonRuntimeException wrap(IntrospectionException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(IllegalAccessException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(InvocationTargetException e)
    {
        return new EasyjsonRuntimeException("InvocationTargetException", e.getTargetException());
    }

    public static EasyjsonRuntimeException wrap(NoSuchMethodException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(IOException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap( InstantiationException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(ClassNotFoundException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(ParseException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(NumberFormatException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(ArrayIndexOutOfBoundsException e)
    {
        return new EasyjsonRuntimeException(e);
    }

    public static EasyjsonRuntimeException wrap(IllegalArgumentException e)
    {
        return new EasyjsonRuntimeException(e);
    }
}

