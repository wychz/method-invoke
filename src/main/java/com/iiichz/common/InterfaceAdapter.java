package com.iiichz.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class InterfaceAdapter implements JsonDeserializer {

    private static String CLASSNAME = null;

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Class<?> klass = getObjectClass(CLASSNAME);
        return jsonDeserializationContext.deserialize(jsonElement, klass);
    }


    public Class<?> getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }

    public void setClassName(String className){
        CLASSNAME = className;
    }
}
