package com.zhuxingsheng.tdd;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/13 23:36
 */
public class Args {

    public static <T> T parse(Class<T> optionsClass, String... params) {

        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];

        try {
            List<String> args = Arrays.asList(params);

            Parameter[] parameters = constructor.getParameters();

            Object[] values = Arrays.stream(parameters).map(parameter -> parseOption(args, parameter)).toArray();

            return (T) constructor.newInstance(values);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object parseOption(List<String> args, Parameter parameter) {
        return PARSERS.get(parameter.getType()).parse(args, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParser> PARSERS = Map.of(boolean.class, new BooleanParser(),
        int.class, IntParser.createIntParser(),
        String.class, new StringParser()
    );

}
