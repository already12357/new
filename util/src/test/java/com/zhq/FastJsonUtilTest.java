package com.zhq;

import com.alibaba.fastjson.JSONArray;
import com.zhq.util.JsonUtil.FastjsonUtil;
import org.junit.Test;

import java.util.List;

public class FastJsonUtilTest {
    @Test
    public void testFastJsonConverter() {
        String jInterListJsonStr = "[2,5,6,3,\"8\"]";
        Object[] objectLists = FastjsonUtil.jStrToArray(jInterListJsonStr);
        System.out.println(objectLists);

        JSONArray test1JArray = FastjsonUtil.arrayToJArray(objectLists);
        System.out.println(test1JArray.toJSONString());
    }
}
