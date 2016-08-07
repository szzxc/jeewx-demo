package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;

import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.JSONToken;

public class JavaObjectDeserializer implements ObjectDeserializer {

    public final static JavaObjectDeserializer instance = new JavaObjectDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        return (T) parser.parse(fieldName);
    }

    public int getFastMatchToken() {
        return JSONToken.LBRACE;
    }
}
