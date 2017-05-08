package com.tesla.cloud.core.annotation;

import com.tesla.cloud.common.enums.LogType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLog {

    String value() default "";

    LogType[] type() default { LogType.OPREATION };

}
