package com.javaweb.utils;

public class validateDataInput {
    public static Boolean StringValidate(String data) {
        return data != null && !data.isEmpty();
    }

    public static Boolean isNumber(String data) {
        try {
            Double.parseDouble(data);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
