package com.zhq.util;

import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.Random;

/**
 * 生成随机数的帮助类
 */
public class RandomUtil {
    /**
     * 生成四位数字的随机数
     */
    public static String randomNum_4() {
        return randomStr_n(4, true, false);
    }

    /**
     * 生成六位随机验证码的随机字符串
     */
    public static String randomStr_6() {
        return randomStr_n(6, false, false);
    }

    /**
     * 生成六位随机验证码的随机数
     */
    public static String randomNum_6() {
        return randomStr_n(6, true, false);
    }

    /**
     * 生成 n 位验证码的生成器
     * @param size 生成验证码的位数
     * @param onlyNum 只有生成数字
     * @param strong 是否采用强随机 ( 可能导致线程阻塞 )
     * @return
     */
    public static String randomStr_n(int size, boolean onlyNum, boolean strong) {
        try {
            Random random = strong? SecureRandom.getInstanceStrong() : new SecureRandom();
            String baseStr = onlyNum?
                    "1234567890" :
                    "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            char[] rtnCode = new char[size];

            for (int i = 0; i < rtnCode.length; i++) {
                rtnCode[i] = baseStr.charAt(random.nextInt(baseStr.length()));
            }

            return new String(rtnCode);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
