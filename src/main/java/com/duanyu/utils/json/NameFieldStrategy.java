package com.duanyu.utils.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class NameFieldStrategy extends PropertyNamingStrategy {

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return nameForMethod(method, defaultName, "get");
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return nameForMethod(method, defaultName, "set");
    }

    private String nameForMethod(AnnotatedMethod method, String defaultName, String methodNamePrefix) {
        String fieldName = method.getName().replaceFirst(methodNamePrefix, "");
        if (fieldName.length() >= 2) {
            StringBuilder result = new StringBuilder(fieldName);
            if (isUpperCase(result.charAt(0)) && isUpperCase(result.charAt(1))) {
                result.setCharAt(0, Character.toLowerCase(result.charAt(0)));
                defaultName = result.toString();
            }
        }
        return defaultName;
    }

    private boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }
}
