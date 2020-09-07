package com.hb0730.boot.admin.project.system.quartz.handler;

import org.springframework.stereotype.Component;

/**
 * @author bing_huang
 */

@Component("taskTest")
public class TaskTest {
    public void obj(Test1 test1) {
        System.out.println("test1 = " + test1);
    }

    public void multipleParams(String s, Boolean b, Long l, Double d, Integer i) {
        System.out.println(String.format("执行多参方法： 字符串类型 %s，布尔类型%s，长整型%s，浮点型%s，整形%d", s, b, l, d, i));
    }

    public void params(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void noParams() {
        System.out.println("执行无参方法");
    }
}
