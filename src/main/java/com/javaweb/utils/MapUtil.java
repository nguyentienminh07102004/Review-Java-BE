package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
    public static <T> T getObject(Map<String, String> params, String key, Class<T> tClass) {
        String str = params.getOrDefault(key, null);
        if(str != null) {
            Object res = null;
            String type = tClass.getTypeName();
            if(type.equalsIgnoreCase("java.lang.Long")) {
                res = str.isEmpty() ? null : Long.valueOf(str);
            } else if (type.equalsIgnoreCase("java.lang.String")) {
                res = str.isEmpty() ? null : str;
            } else if(type.equalsIgnoreCase("java.lang.Integer")) {
                res = str.isEmpty() ? null : Integer.valueOf(str);
            }
            return tClass.cast(res);
        }
        return null;
    }
}
