package com.letv.tbtSps.common.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 16-10-14
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Convertable {
    /**
     * @return the method name that convert to another type
     */
    String valueMethod() default "value";

    /**
     * @return the method name that convert from another type
     */
    String ofMethod() default "of";
}
