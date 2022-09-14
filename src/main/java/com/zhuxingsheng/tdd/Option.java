package com.zhuxingsheng.tdd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/14 00:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER})
public @interface Option {

    String value() default "";

}
