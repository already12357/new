package com.zhq.dynamicproxy;

import com.zhq.dynamicproxy.client.Client;
import org.junit.Test;

public class DProxyTest {
    @Test
    public void testDynamicProxy() {
        Client client = new Client();
        client.buyPerfume();
    }
}
