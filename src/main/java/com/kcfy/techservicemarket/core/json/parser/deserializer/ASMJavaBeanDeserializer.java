package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.kcfy.techservicemarket.core.json.JSONException;
import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.Feature;
import com.kcfy.techservicemarket.core.json.parser.JSONScanner;
import com.kcfy.techservicemarket.core.json.parser.ParserConfig;
import com.kcfy.techservicemarket.core.json.util.FieldInfo;

public abstract class ASMJavaBeanDeserializer implements ObjectDeserializer {

    protected InnerJavaBeanDeserializer serializer;

    public ASMJavaBeanDeserializer(ParserConfig mapping, Class<?> clazz){
        serializer = new InnerJavaBeanDeserializer(mapping, clazz);

        serializer.getFieldDeserializerMap();
    }

    public abstract Object createInstance(DefaultJSONParser parser, Type type);

    public InnerJavaBeanDeserializer getInnterSerializer() {
        return serializer;
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return (T) serializer.deserialze(parser, type, fieldName);
    }

    public int getFastMatchToken() {
        return serializer.getFastMatchToken();
    }

    public Object createInstance(DefaultJSONParser parser) {
        return serializer.createInstance(parser, serializer.getClazz());
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        return mapping.createFieldDeserializer(mapping, clazz, fieldInfo);
    }

    public FieldDeserializer getFieldDeserializer(String name) {
        return serializer.getFieldDeserializerMap().get(name);
    }
    
    public Type getFieldType(String name) {
        return serializer.getFieldDeserializerMap().get(name).getFieldType();
    }

    public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        JSONScanner lexer = (JSONScanner) parser.getLexer(); // xxx

        FieldDeserializer fieldDeserializer = serializer.getFieldDeserializerMap().get(key);

        if (fieldDeserializer == null) {
            if (!parser.isEnabled(Feature.IgnoreNotMatch)) {
                throw new JSONException("setter not found, class " + serializer.getClass() + ", property " + key);
            }

            lexer.nextTokenWithColon();
            parser.parse(); // skip

            return false;
        }

        lexer.nextTokenWithColon(fieldDeserializer.getFastMatchToken());
        fieldDeserializer.parseField(parser, object, objectType, fieldValues);
        return true;
    }

    public final class InnerJavaBeanDeserializer extends JavaBeanDeserializer {

        private InnerJavaBeanDeserializer(ParserConfig mapping, Class<?> clazz){
            super(mapping, clazz);
        }

        public boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
            return ASMJavaBeanDeserializer.this.parseField(parser, key, object, objectType, fieldValues);
        }

        public FieldDeserializer createFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
            return ASMJavaBeanDeserializer.this.createFieldDeserializer(mapping, clazz, fieldInfo);
        }
    }

}
