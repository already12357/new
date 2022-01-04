package com.template._1.third;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 第三方接口的常量配置类
 * @author Eddie Zhang
 */
public class ThirdConstant {
    /**
     * 接口 (1) [ 变量名 NAME1 ]
     */
    // 接口名称 ( URL_[***] )
    public static final String URL_NAME1 = "";
    // 接口测试数据 ( TEST_DATA_[***] ) , 以函数形式返回
    public static String TEST_DATA_NAME1() {
        JSONObject testData = new JSONObject();

        /**
         * 拼接测试数据
         */

        return testData.toJSONString();
    }
    // 接口请求类型 ( METHOD_[***] )
    public static final String METHOD_NAME1 = "";
    // 数据返回类型 ( DATA_TYPE_[***] )
    public static final String DATA_TYPE_NAME1 = "";

    /**
     * 接口 (2) [ 变量名 NAME2 ]
     */
    public static final String URL_NAME2 = "";
    public static String TEST_DATA_NAME2() {
        JSONObject testData = new JSONObject();
        /**
         * 拼接测试数据
         */
        return testData.toJSONString();
    }
    public static final String METHOD_NAME2 = "";
    public static final String DATA_TYPE_NAME2 = "";
}
