package com.zhuxingsheng.tdd;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/13 23:36
 */
public class Args {

    public static <T> T parse(Class<T> optionsClass, String... params) {

        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];

        try {
            Parameter parameter = constructor.getParameters()[0];
            Option option = parameter.getAnnotation(Option.class);
            List<String> args = Arrays.asList(params);
            return (T) constructor.newInstance(args.contains("-" + option.value()));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
