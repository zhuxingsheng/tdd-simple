package com.zhuxingsheng.tdd;

import java.util.List;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/25 23:46
 */
class StringParser implements OptionParser {

    @Override
    public Object parse(List<String> args, Option option) {
        Object value;
        int index = args.indexOf("-" + option.value());
        value = args.get(index + 1);
        return value;
    }

}
