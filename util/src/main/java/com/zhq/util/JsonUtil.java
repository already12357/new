package com.zhq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    /**
     * 将 JSON 数组字符串转换为对应的类型 List 对象
     * @param jsonStr 传入的 JSON 数组字符串
     * @param convertType 转换类型
     * @return
     */
    public static List jArrayToList(String jsonStr, Class convertType) {
        JSONArray jArray = JSON.parseArray(jsonStr);
        List retArray = new ArrayList();
        Method valueOfMethod = null;

        try {
            // 反射调用转换类型的 valueOf 对象, 如 Integer.valueOf(...)
            valueOfMethod = convertType.getMethod("valueOf", String.class);

            for (int i = 0; i < jArray.size(); i++) {
                String arrElement = jArray.getString(i);
                Object convertedValue = valueOfMethod.invoke(null, arrElement);
                // 调用后转换
                retArray.add(convertType.cast(convertedValue));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return retArray;
    }
}
