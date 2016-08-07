package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;

import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;

public interface ObjectDeserializer {
    <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName);
    
    int getFastMatchToken();
}
