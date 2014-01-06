package com.github.ancosen.easyjson.json.serializer.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Utils {

    private static Map<String, Class<?>> SUBST_MAP = new TreeMap<String, Class<?>>();
    private static Map<String, Class<?>> SIMPLE_MAP = new TreeMap<String, Class<?>>();

    static {
        SUBST_MAP.put(Byte.class.getName(), Byte.TYPE);
        SUBST_MAP.put(Short.class.getName(), Short.TYPE);
        SUBST_MAP.put(Integer.class.getName(), Integer.TYPE);
        SUBST_MAP.put(Long.class.getName(), Long.TYPE);
        SUBST_MAP.put(Float.class.getName(), Float.TYPE);
        SUBST_MAP.put(Double.class.getName(), Double.TYPE);
        SUBST_MAP.put(Boolean.class.getName(), Boolean.TYPE);
        SUBST_MAP.put(Character.class.getName(), Character.TYPE);
        SIMPLE_MAP.put(String.class.getName(), String.class);
        SIMPLE_MAP.put(ArrayList.class.getName(), ArrayList.class);
        SIMPLE_MAP.put(LinkedList.class.getName(), LinkedList.class);
        SIMPLE_MAP.put(HashMap.class.getName(), HashMap.class);
        SIMPLE_MAP.put(LinkedHashMap.class.getName(), LinkedHashMap.class);
        SIMPLE_MAP.put(BigDecimal.class.getName(), BigDecimal.class);
        SIMPLE_MAP.put(Long.class.getName(), Long.class);
        SIMPLE_MAP.put(Integer.class.getName(), Integer.class);
    }

public static Class<?> getClassType(Object arg, boolean substPrimitiveWrapper){
    Class<?> classType = null;
    String className = null;
    Class<?> substClass = null;

    if(arg != null ){
        //making default classType
        classType = arg.getClass();
        if(substPrimitiveWrapper){
            className = classType.getName();
            substClass = (Class<?>)SUBST_MAP.get(className);
            if(substClass != null){
                classType = substClass;
            }

        }
    }
    return classType;
}

public static boolean isJDKClass(Object arg){
    Class<?> classType = getClassType(arg, true);
    boolean isJDKClass = false;
    if(classType!=null){
        assert classType!=null;
        String className = classType.getName();
        Boolean isFound = SIMPLE_MAP.containsKey(className);
        if(Boolean.TRUE.equals(isFound)){
            isJDKClass = true; //this is predefined class
        }
        boolean isPrimitiveType = classType.isPrimitive();
        if(isPrimitiveType){
            isJDKClass = true; //this is primitive type or wrapper class
        }
    }

    return isJDKClass;
 }
} 