package com.zhq.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据类, 用于对分页数据进行封装, 保存有
 * @author Eddie Zhang
 */
public class PageData<T> {
    // 默认的分页大小
    public static final int DEFAULT_SIZE = 8;
    // 默认的分页编号
    public static final int DEFAULT_INDEX = 0;

    // 分页的大小
    private Integer pageSize;
    // 当前分页的编号 ( 从 0 开始 )
    private Integer pageIndex;
    // 分页的数据内容
    private List<T> pageContent;

    public PageData(List<T> data, int pageSize, int pageIndex) {
        int pageEnd = Math.min((pageIndex + 1) * pageSize, data.size());
        pageContent = new ArrayList<T>();
        pageContent.addAll(data.subList(pageSize * pageIndex, pageEnd));
        pageIndex = pageIndex;
        pageSize = pageSize;
    }

    public PageData(List<T> data, int pageSize) {
        this(data, pageSize, DEFAULT_INDEX);
    }

    public PageData(List<T> data) {
        this(data, DEFAULT_SIZE, DEFAULT_INDEX);
    }

    
    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }
    public List<T> getPageContent() {
        return pageContent;
    }

    public Integer getSize() {
        return pageContent.size();
    }
}
