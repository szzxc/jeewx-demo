package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.kcfy.techservicemarket.core.json.JSONException;
import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.JSONToken;

public class TimestampDeserializer implements ObjectDeserializer {

    public final static TimestampDeserializer instance = new TimestampDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        Object val = parser.parse();
        
        if (val == null) {
            return null;
        }

        if (val instanceof Date) {
            return (T) new Timestamp(((Date) val).getTime());
        }

        if (val instanceof Number) {
            return (T) new Timestamp(((Number) val).longValue());
        }

        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }
            
            DateFormat dateFormat = parser.getDateFormat();
            try {
                Date date = (Date) dateFormat.parse(strVal);
                return (T) new Timestamp(date.getTime());
            } catch (ParseException e) {
                // skip
            }

            long longVal = Long.parseLong(strVal);
            return (T) new Timestamp(longVal);
        }

        throw new JSONException("parse error");
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
