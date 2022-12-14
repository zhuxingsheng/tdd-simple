package com.zhuxingsheng.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author zhuxingsheng@gmail.com
 * @description: TODO
 * @date 2022/9/3 21:15
 */
public class ArgsTest {

    //第一步：构思软件被使用的方式，把握对外接口的方向

    //第二步：大致构思功能的实现方式，划分所需的组件以及组件间的关系

    //这一步，开始开写测试，并要通过测试，这个思路是对的，但这样实际操作时，步子太大。
    //不能通过测试来控制我们的节奏，所以需要第三步

    //第三步：拆分功能点，考虑happy path,sad path
    //拆分成single option与multi option

    //第四步：针对拆分的结果 编写测试，进入红/绿/重构 循环

    //通过几次的红绿循环，已经实现了一大块功能。此时有两个选择，一是继续开始功能，二是重构

    //重构的条件：一是测试都是绿的，二是坏味道足够明显

    // 当前所有测试都是绿的，并且发现了明显的坏味道parseOption，开始重构

    /**
     * 第一个示例：
     * <p>
     * -l -p 8080 -d /usr/logs
     * <p>
     * <p>
     * single option:
     * <p> -Boolean
     * TODO [-l]
     * <p> -Integer
     * TODO [-p 8080]
     * <p> -String
     * TODO [-d /usr/logs]
     * <p>
     * sad path
     * <p>
     * -l t f / -l f t
     * <p>
     * -p / -p 9090 8080
     */
    @Test
    public void should_set_boolean_option_to_true_if_flag_present() {
        BooleanOption options = Args.parse(BooleanOption.class, "-l");
        Assertions.assertTrue(options.logging());
    }

    @Test
    public void should_set_boolean_option_to_false_if_flag_not_present() {
        BooleanOption options = Args.parse(BooleanOption.class);
        Assertions.assertFalse(options.logging());
    }

    /**
     * 实现第二个TODO [-p 8080]
     */
    @Test
    public void should_set_int_as_option_value() {
        IntOption options = Args.parse(IntOption.class, "-p", "8080");
        Assertions.assertEquals(8080, options.port());
    }

    static record IntOption(@Option("p") int port) {

    }

    /**
     * 实现第三个TODO [-d /usr/logs]
     */
    @Test
    public void should_set_string_as_option_value() {
        StringOption options = Args.parse(StringOption.class, "-d", "/usr/logs");
        Assertions.assertEquals("/usr/logs", options.directory());
    }

    static record StringOption(@Option("d") String directory) {

    }


    static record BooleanOption(@Option("l") boolean logging) {

    }

    @Test
    public void should_multi_option_value() {

        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");

        Assertions.assertEquals(true, options.logging());
        Assertions.assertEquals(8080, options.port());
        Assertions.assertEquals("/usr/logs", options.directory());

    }

    static record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    /**
     * -g this is a list -d 1 2 -3 5
     */
    @Test
    @Disabled
    public void should_example_2() {
        ListOptions options = Args.parse(ListOptions.class, "-g", "this", "is", "a", "list", "-d", "1", "3", "5");

        Assertions.assertArrayEquals(new String[]{"this", "is", "a", "list"}, options.group());
        Assertions.assertArrayEquals(new int[]{1, 3, 5}, options.decimals());
    }


    private record Options(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {

    }

    private record ListOptions(@Option("g") String[] group, @Option("d") int[] decimals) {

    }

}
