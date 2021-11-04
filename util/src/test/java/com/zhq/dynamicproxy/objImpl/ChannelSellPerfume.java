package com.zhq.dynamicproxy.objImpl;

import com.zhq.dynamicproxy.objapi.SellPerfume;

/**
 * 定义具体的对象, 实现接口
 */
public class ChannelSellPerfume implements SellPerfume {
    @Override
    public void sellPerfume() {
        System.out.println("sell channel perfume!");
    }
}
