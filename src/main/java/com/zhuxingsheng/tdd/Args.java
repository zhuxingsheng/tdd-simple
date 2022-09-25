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
            value = parseBoolean(args, option);
        }

        if (parameter.getType() == int.class) {
            value = parseInt(args, option);
        }

        if (parameter.getType() == String.class) {
            value = parseString(args, option);
        }
        return value;
    }

    interface OptionParser {

        Object parse(List<String> args, Option option);

    }

    private static Object parseString(List<String> args, Option option) {
        return new StringParser().parse(args, option);
    }

    private static Object parseInt(List<String> args, Option option) {
        return new IntParser().parse(args, option);
    }

    private static Object parseBoolean(List<String> args, Option option) {
        return new BooleanParser().parse(args, option);
    }

    static class StringParser implements OptionParser {

        @Override
        public Object parse(List<String> args, Option option) {
            Object value;
            int index = args.indexOf("-" + option.value());
            value = args.get(index + 1);
            return value;
        }

    }

    static class IntParser implements OptionParser {

        @Override
        public Object parse(List<String> args, Option option) {
            Object value;
            int index = args.indexOf("-" + option.value());
            value = Integer.valueOf(args.get(index + 1));
            return value;
        }

    }

    static class BooleanParser implements OptionParser {

        @Override
        public Object parse(List<String> args, Option option) {
            Object value;
            value = args.contains("-" + option.value());
            return value;
        }

    }

}
