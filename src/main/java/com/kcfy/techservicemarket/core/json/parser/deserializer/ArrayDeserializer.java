package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

import com.kcfy.techservicemarket.core.json.JSONArray;
import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.JSONLexer;
import com.kcfy.techservicemarket.core.json.parser.JSONToken;
import com.kcfy.techservicemarket.core.json.util.TypeUtils;

public class ArrayDeserializer implements ObjectDeserializer {

    public final static ArrayDeserializer instance = new ArrayDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        final JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken(JSONToken.COMMA);
            return null;
        }
        
        if (lexer.token() == JSONToken.LITERAL_STRING) {
            byte[] bytes = lexer.bytesValue();
            lexer.nextToken(JSONToken.COMMA);
            return (T) bytes;
        }
        
        JSONArray array = new JSONArray();
        parser.parseArray(array);

        return toObjectArray(parser, (Class<T>) clazz, array);
    }

    @SuppressWarnings("unchecked")
    private <T> T toObjectArray(DefaultJSONParser parser, Class<T> clazz, JSONArray array) {
        if (array == null) {
            return null;
        }
        
        int size = array.size();

        Class<?> componentType = clazz.getComponentType();
        Object objArray = Array.newInstance(componentType, size);
        for (int i = 0; i < size; ++i) {
            Object value = array.get(i);

            if (componentType.isArray()) {
                Object element = toObjectArray(parser, componentType, (JSONArray) value);
                Array.set(objArray, i, element);
            } else {
                Object element = TypeUtils.cast(value, componentType, parser.getConfig());
                Array.set(objArray, i, element);
            }
        }
        return (T) objArray; // TODO
    }

    public int getFastMatchToken() {
        return JSONToken.LBRACKET;
    }
}
