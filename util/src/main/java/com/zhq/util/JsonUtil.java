package com.zhq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    /**
     * 将 JSON String 对象转换为对应的 List 对象
     * @param jsonStr 传入的 JSON 数组字符串
     * @param convertType 转换类型
     * @return
     */
    public List jArrayToList(String jsonStr, Class convertType) {
        JSONArray jArray = JSON.parseArray(jsonStr);
        List retArray = new ArrayList();

        for (int i = 0; i < jArray.size(); i++) {
            retArray.add(convertType.cast(jArray.get(i)));
        }

        return retArray;
    }
}
