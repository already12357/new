package com.zhq.util.pageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理和分页有关的事项
 */
public class PageUtil {
    /**
     * 根据元素数量，当前页号，每页的大小，获取遍历当前页的起始编号
     * @param elements 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页起始的序号
     */
    public static int beginIndex(List elements, int currentPage, int pageSize) {
        int beginIndex = -1;
        int elementSize = elements.size();

        if (currentPage * pageSize < elementSize) {
            beginIndex = currentPage * pageSize;
        }

        return beginIndex;
    }

    /**
     * 根据元素数量，当前页号，每页的大小，获取遍历当前页的结束序号 ( 使用不等号连接遍历 )
     * @param elements 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页结束的序号
     */
    public static int endIndex(List elements, int currentPage, int pageSize) {
        int endIndex = -1;
        int elementSize = elements.size();
        int nextPage = currentPage + 1;

        // 前提需要满足
        if (currentPage * pageSize < elementSize) {
            endIndex = Math.min(pageSize * nextPage, elementSize);
        }

        return endIndex;
    }

    /**
     * 根据当前元素集合，页号 和 每页的大小 来返回当前页中的数据集合
     * @param elements 查询得到的所有结果
     * @param currentPage 当前页号 ( 从 0 开始 )
     * @param pageSize 每页元素数量的大小 ( 大于 0 )
     * @return 该页显示的元素总数
     */
    public static <T> List<T> pageList(List<T> elements, int currentPage, int pageSize) {
        List<T> retList = new ArrayList<>();
        int elementSize = elements.size();
        int nextPage = currentPage + 1;
        int beginIndex = beginIndex(elements, currentPage, pageSize);
        int endIndex = endIndex(elements, currentPage, pageSize);

        for (int i = beginIndex; i < endIndex; i++) {
            retList.add(elements.get(i));
        }

        return retList;
    }
}
