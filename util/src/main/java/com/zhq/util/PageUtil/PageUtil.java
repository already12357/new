package com.zhq.util.PageUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 用于处理和分页有关的事项
 */
public class PageUtil {
    /**
     * 根据元素数量，当前页号，每页的大小，获取遍历当前页的起始编号
     * @param totalSize 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页起始的序号
     */
    public static int beginIndex(int totalSize, int currentPage, int pageSize) {
        int beginIndex = -1;

        if (currentPage * pageSize < totalSize) {
            beginIndex = currentPage * pageSize;
        }

        return beginIndex;
    }

    /**
     * 根据元素数量，当前页号，每页的大小，获取遍历当前页的结束序号 ( 使用不等号连接遍历 )
     * @param totalSize 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页结束的序号
     */
    public static int endIndex(int totalSize, int currentPage, int pageSize) {
        int endIndex = -1;
        int nextPage = currentPage + 1;

        // 前提需要满足
        if (currentPage * pageSize < totalSize) {
            endIndex = Math.min(pageSize * nextPage, totalSize);
        }

        return endIndex;
    }


    /**
     * 对 List 对象进行分页
     * @param list 列表对象
     * @param currentPage 当前页号
     * @param pageSize 每页的大小
     * @return
     */
    public static List pageList(List list, int currentPage, int pageSize) {
        List pagedList = Arrays.asList(pageCollection(list, currentPage, pageSize));
        return new ArrayList(pagedList);
    }

    public static Set pageSet(Set set, int currentPage, int pageSize) {
        return new HashSet(Arrays.asList(pageCollection(set, currentPage, pageSize)));
    }

    /**
     * 根据当前(元素集合 | 数组对象)，页号 和 每页的大小 来返回当前页中的数据集合, 以数组形式返回
     * @param collection 查询得到的所有结果
     * @param currentPage 当前页号 ( 从 0 开始 )
     * @param pageSize 每页元素数量的大小 ( 大于 0 )
     * @return 该页显示的元素总数
     */
    private static Object[] pageCollection(Collection collection, int currentPage, int pageSize) {
        int totalSize = collection.size();
        int beginIndex = beginIndex(totalSize, currentPage, pageSize);
        int endIndex = endIndex(totalSize, currentPage, pageSize);

        if (-1 != beginIndex && -1 != endIndex) {
            Object[] retArray = new Object[endIndex - beginIndex];
            Object[] collectionArray = collection.toArray();
            int pageIterator = 0;

            for (int i = beginIndex; i < endIndex; i++) {
                retArray[pageIterator++] = collectionArray[i];
            }

            return retArray;
        }

        return null;
    }


    private static Object[] pageArray(Object[] array, int currentPage, int pageSize) {
        int totalSize = array.length;
        int beginIndex = beginIndex(totalSize, currentPage, pageSize);
        int endIndex = endIndex(totalSize, currentPage, pageSize);

        if (-1 != beginIndex && -1 != endIndex) {
            Object[] retArray = new Object[endIndex - beginIndex];
            int pageIterator = 0;

            for (int i = beginIndex; i < endIndex; i++) {
                retArray[pageIterator++] = array[i];
            }

            return retArray;
        }

        return null;
    }
}
