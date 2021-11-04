package com.zhq.dynamicproxy.client;

import com.zhq.dynamicproxy.objImpl.ChannelSellPerfume;
import com.zhq.dynamicproxy.objapi.SellPerfume;
import com.zhq.dynamicproxy.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * 需要物品的客户对象
 */
public class Client {
    public void buyPerfume() {
        // 定义具体对象
        ChannelSellPerfume channelSellPerfume = new ChannelSellPerfume();
        // 定义动态的代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(channelSellPerfume);
        SellPerfume sellPerfume = (SellPerfume) Proxy.newProxyInstance(channelSellPerfume.getClass().getClassLoader(), channelSellPerfume.getClass().getInterfaces(), proxyFactory);

        sellPerfume.sellPerfume();
    }
}
