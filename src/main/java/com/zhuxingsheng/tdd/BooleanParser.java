package com.zhuxingsheng.tdd;

import java.util.List;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/25 23:46
 */
class BooleanParser implements OptionParser {

    @Override
    public Object parse(List<String> args, Option option) {
        Object value;
        value = args.contains("-" + option.value());
        return value;
    }

}
