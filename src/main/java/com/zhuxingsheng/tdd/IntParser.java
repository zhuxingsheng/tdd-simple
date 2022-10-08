package com.zhuxingsheng.tdd;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/25 23:46
 */
class IntParser extends StringParser {

    //使用factory 代替constructor,因为factory方法能inline
    private IntParser() {
        super(Integer::valueOf);
    }

    public static OptionParser createIntParser() {
        return new StringParser(Integer::valueOf);
    }

}
