package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLongArray;

import com.kcfy.techservicemarket.core.json.JSONArray;
import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.JSONToken;

public class AtomicLongArrayDeserializer implements ObjectDeserializer {

    public final static AtomicLongArrayDeserializer instance = new AtomicLongArrayDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        if (parser.getLexer().token() == JSONToken.NULL) {
            parser.getLexer().nextToken(JSONToken.COMMA);
            return null;
        }

        JSONArray array = new JSONArray();
        parser.parseArray(array);

        AtomicLongArray atomicArray = new AtomicLongArray(array.size());
        for (int i = 0; i < array.size(); ++i) {
            atomicArray.set(i, array.getLong(i));
        }

        return (T) atomicArray;
    }

    public int getFastMatchToken() {
        return JSONToken.LBRACKET;
    }
}
