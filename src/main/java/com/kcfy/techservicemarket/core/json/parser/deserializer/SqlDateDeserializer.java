package com.kcfy.techservicemarket.core.json.parser.deserializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.kcfy.techservicemarket.core.json.JSONException;
import com.kcfy.techservicemarket.core.json.parser.DefaultJSONParser;
import com.kcfy.techservicemarket.core.json.parser.JSONScanner;
import com.kcfy.techservicemarket.core.json.parser.JSONToken;

public class SqlDateDeserializer implements ObjectDeserializer {

    public final static SqlDateDeserializer instance = new SqlDateDeserializer();

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        final JSONScanner lexer = (JSONScanner) parser.getLexer();

        if (lexer.token() == JSONToken.NULL) {
            lexer.nextToken(JSONToken.COMMA);
            return null;
        }

        if (lexer.token() == JSONToken.COMMA) {
            String key = lexer.scanSymbol(parser.getSymbolTable());

            if ("val" != key) {
                throw new JSONException("syntax error");
            }

            lexer.nextTokenWithColon(JSONToken.LITERAL_INT);

            if (lexer.token() != JSONToken.LITERAL_INT) {
                throw new JSONException("syntax error");
            }

            long val = lexer.longValue();

            lexer.nextToken(JSONToken.RBRACE);

            if (lexer.token() != JSONToken.RBRACE) {
                throw new JSONException("syntax error");
            }
            lexer.nextToken(JSONToken.COMMA);

            return (T) new java.sql.Date(val);
        }

        Object val = parser.parse();
        if (val == null) {
            return null;
        }

        if (val instanceof Date) {
            val = new java.sql.Date(((Date) val).getTime());
        } else if (val instanceof Number) {
            val = (T) new java.sql.Date(((Number) val).longValue());
        } else if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() == 0) {
                return null;
            }

            long longVal;

            JSONScanner dateLexer = new JSONScanner(strVal);
            if (dateLexer.scanISO8601DateIfMatch()) {
                longVal = dateLexer.getCalendar().getTimeInMillis();
            } else {

                DateFormat dateFormat = parser.getDateFormat();
                try {
                    Date date = (Date) dateFormat.parse(strVal);
                    return (T) new java.sql.Date(date.getTime());
                } catch (ParseException e) {
                    // skip
                }

                longVal = Long.parseLong(strVal);
            }
            return (T) new java.sql.Date(longVal);
        } else {
            throw new JSONException("parse error : " + val);
        }

        return (T) val;
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_INT;
    }
}
