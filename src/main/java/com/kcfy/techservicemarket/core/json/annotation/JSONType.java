package com.kcfy.techservicemarket.core.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.kcfy.techservicemarket.core.json.serializer.SerializerFeature;

/**
 * @author wenshao<szujobs@hotmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface JSONType {

    String[] orders() default {};

    SerializerFeature[] serialzeFeatures() default {};
}
