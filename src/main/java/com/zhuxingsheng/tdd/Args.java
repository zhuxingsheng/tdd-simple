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
        Option option = parameter.getAnnotation(Option.class);

        Object value = null;
        if (parameter.getType() == boolean.class) {
            value = args.contains("-" + option.value());
        }

        if (parameter.getType() == int.class) {
            int index = args.indexOf("-" + option.value());
            value = Integer.valueOf(args.get(index + 1));
        }

        if (parameter.getType() == String.class) {
            int index = args.indexOf("-" + option.value());
            value = args.get(index + 1);
        }
        return value;
    }

}
