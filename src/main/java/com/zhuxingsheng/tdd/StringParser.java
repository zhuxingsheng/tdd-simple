package com.zhuxingsheng.tdd;

import java.util.List;
import java.util.function.Function;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/25 23:46
 */
class StringParser implements OptionParser {

    Function<String, Object> parseValue = String::valueOf;

    public StringParser() {
    }

    public StringParser(Function<String, Object> parseValue) {
        this.parseValue = parseValue;
    }

    @Override
    public Object parse(List<String> args, Option option) {
        int index = args.indexOf("-" + option.value());
        String value = args.get(index + 1);
        return getValue(value);
    }

    protected Object getValue(String value) {

        return parseValue.apply(value);
    }

}
