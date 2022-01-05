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
    // 接口的中文名称
    public static final String CHI_NAME1 = "企业信息查询接口";
    // 接口的描述信息
    public static final String DESCRIPTION_NAME1 = "该内容是企业查询接口的接口内容，用于生成对应的企业接口内容";
    // 接口 URL ( URL_[***] )
    public static final String URL_NAME1 = "http://localhost:8090/epoint-web-znsb/frame/page/rest/controller";
    // 接口请求类型 ( METHOD_[***] )
    public static final String METHOD_NAME1 = "post";
    // 数据返回类型 ( DATA_TYPE_[***] )
    public static final String DATA_TYPE_NAME1 = "";
    // 接口测试请求数据 ( TEST_DATA_[***] ) , 以函数形式返回, 用于生成接口文档
    public static final String TEST_REQ_DATA_NAME1() {
        JSONObject testData = new JSONObject();

        /**
         * 拼接测试数据, 直接写死
         */
        testData.put("hello", "yest");

        return JSONObject.toJSONString(testData, true);
    }
    // 接口测试返回数据 ( TEST_DATA_[***] ) , 以函数形式返回, 用于生成接口文档
    public static final String TEST_RES_DATA_NAME1() {
        JSONObject testData = new JSONObject();

        /**
         * 拼接返回数据, 直接写死
         */
        testData.put("fdsa", "faf");

        return JSONObject.toJSONString(testData, true);
    }



    /**
     * 接口 (2) [ 变量名 NAME2 ]
     */
    public static final String CHI_NAME2 = "";
    public static final String DESCRIPTION_NAME2 = "";
    public static final String URL_NAME2 = "";
    public static final String METHOD_NAME2 = "";
    public static final String DATA_TYPE_NAME2 = "";
    public static final String TEST_REQ_DATA_NAME2() {
        JSONObject testData = new JSONObject();

        /**
         * 拼接测试数据, 直接写死
         */

        return testData.toJSONString();
    }
    public static final String TEST_RES_DATA_NAME2() {
        JSONObject testData = new JSONObject();

        /**
         * 拼接返回数据, 直接写死
         */

        return testData.toJSONString();
    }
}
