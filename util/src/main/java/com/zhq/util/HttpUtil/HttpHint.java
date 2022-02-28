package com.zhq.util.HttpUtil;

/**
 * 部分 Http 设置
 */
public enum HttpHint {
    REQ_HEADER_AUTHORITY("authority"),
    REQ_HEADER_METHOD("method"),
    REQ_HEADER_CONTENT_TYPE("content-type"),
    REQ_HEADER_ACCEPT_ENCODING("accept-encoding"),
    REQ_HEADER_ACCEPT("accept");

    // 属性名称
    private String propertyName;

    HttpHint(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
