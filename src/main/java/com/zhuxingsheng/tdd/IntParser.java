package com.zhuxingsheng.tdd;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/25 23:46
 */
class IntParser extends StringParser {

    public static OptionParser createIntParser() {
        return new StringParser(Integer::valueOf);
    }

}
