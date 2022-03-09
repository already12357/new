package com.demo.util;

import com.zhq.util.RandomUtil;

/**
 * 随机数帮助类的说明 demo
 * @author Eddie Zhang
 */
public class RandomUtil_Demo {
    public static void main(String[] args) {
        RandomUtil_Demo instance = new RandomUtil_Demo();

//        instance.demo1();
        instance.demo2();
    }

    /**
     * demo :randomNum_4, randomStr_6
     */
    public void demo1() {
        System.out.println(RandomUtil.randomNum_4());
        System.out.println(RandomUtil.randomStr_6());
    }

    /**
     * demo :randomStr_n
     */
    public void demo2() {
        System.out.println(RandomUtil.randomStr_n(10, false, false));
    }
}
