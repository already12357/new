package com.zhq.util.PageUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 数据类, 用于对分页数据进行封装
 * @author Eddie Zhang
 */
public class PageData<T> {
    // 默认的分页大小
    public static final int DEFAULT_SIZE = 8;
    // 默认的分页编号
    public static final int DEFAULT_INDEX = 0;

    // 分页的大小 ( 每页大小 )
    private Integer pageSize = -1;
    // 数据总量大小
    private Integer totalSize = -1;
    // 实际显示在该页的有多少条数据
    private Integer pageCount = -1;
    // 当前分页的编号 ( 从 0 开始 )
    private Integer pageIndex = -1;
    // 分页的数据内容
    private List<T> pageContent = null;
    // 是否有下一页数据
    private boolean hasNextPage = false;

    public PageData(List<T> data, int pageSize, int pageIndex) {
        if (validPageIndex(pageIndex, pageSize, data)) {
            int pageEnd = Math.min((pageIndex + 1) * pageSize, data.size());

            this.pageContent = new ArrayList<T>();
            this.pageContent.addAll(data.subList(pageSize * pageIndex, pageEnd));

            this.pageCount = pageContent.size();
            this.pageIndex = pageIndex;
            this.pageSize = pageSize;
            this.totalSize = data.size();
            this.hasNextPage = (pageIndex + 1) * pageSize < totalSize;
        }
    }

    public PageData(List<T> data, int pageSize) {
        this(data, pageSize, DEFAULT_INDEX);
    }

    public PageData(List<T> data) {
        this(data, DEFAULT_SIZE, DEFAULT_INDEX);
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public boolean hasNextPage() {
        return hasNextPage;
    }

    /**
     * 判断当前传入分页对象和分页信息是否有效
     * @param currentPage 当前页号 ( 默认从零开始 )
     * @param pageSize 每页的大小
     * @param collection 需要分页的总数据
     * @return
     */
    public boolean validPageIndex(int currentPage, int pageSize, Collection collection) {
        return null != collection
                && currentPage >= 0
                && pageSize > 0
                && currentPage * pageSize < collection.size();
    }
}
