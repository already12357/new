package com.zhq.dynamicproxy.proxy;

import org.junit.Before;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理工程, 根据需要不同物品的需要的接口, 动态生成不同的代理对象
 */
public class ProxyFactory implements InvocationHandler {
    // 真实的对象
    private Object realObject;

    public ProxyFactory(Object realObject) {
        this.realObject = realObject;
    }

    /**
     *
     * @param proxy 传入的动态代理类对象
     * @param method 代理对象调用的方法
     * @param args 代理方法的参数
     * @return 生成的真实对象
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用传入的 realObject 的接口方法
        Object createdRealObject = method.invoke(realObject, args);

        return createdRealObject;
    }
}
