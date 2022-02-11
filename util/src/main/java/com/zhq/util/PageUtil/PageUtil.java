package com.zhq.util.PageUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 用于处理和分页有关的事项
 * @author Eddie Zhang
 */
public class PageUtil {
    /**
     * 根据元素数量，当前页号，每页的大小，获取遍历当前页的起始编号 ( 循环是包含 )
     * @param totalSize 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页起始的序号
     */
    public static int beginIndex(int totalSize, int currentPage, int pageSize) {
        if (isValidPageIndex(totalSize, currentPage, pageSize)) {
            return currentPage * pageSize;
        }
        else {
            return -1;
        }
    }

    /**
     * 根据元素数量，(当前页号 | 当前元素编号)，每页的大小，获取遍历当前页的结束序号 ( 循环时不包含 )
     * @param totalSize 查询到的元素总数
     * @param currentPage 当前页号 ( 页号从 0 开始 )
     * @param pageSize 当前页的大小 ( 大于 0 )
     * @return 遍历当前页结束的序号
     */
    public static int endIndexByPageIndex(int totalSize, int currentPage, int pageSize) {
        if (isValidPageIndex(totalSize, currentPage, pageSize)) {
            return Math.min(pageSize * (currentPage + 1), totalSize);
        }
        else {
            return -1;
        }
    }

    public static int endIndexByItemIndex(int totalSize, int currentItemIndex, int pageSize) {
        if (isValidItemIndex(totalSize, currentItemIndex, pageSize)) {
            return Math.min(pageSize + currentItemIndex, totalSize);
        }
        else {
            return -1;
        }
    }


    /**
     * 对 List 对象进行分页 ( 根据页号 或 元素标识号 )
     * @param list 列表对象
     * @param currentPage 当前页号
     * @param pageSize 每页的大小
     * @return
     */
    public static List pageListFromPage(List list, int currentPage, int pageSize) {
        Object[] listArray = pageCollectionFromPage(list, currentPage, pageSize);

        if (null != listArray) {
            return new ArrayList(Arrays.asList(listArray));
        }
        else {
            return new ArrayList();
        }
    }

    public static List pageListFromItem(List list, int currentItemIndex, int pageSize) {
        Object[] listArray = pageCollectionFromItem(list, currentItemIndex, pageSize);

        if (null != listArray) {
            return new ArrayList(Arrays.asList(listArray));
        }
        else {
            return new ArrayList();
        }
    }




    /**
     * 根据当前(元素集合 | 数组对象)，(页号 | 元素编号) 和 每页的大小 来返回当前页中的数据集合, 以数组形式返回
     * @param collection 查询得到的所有结果
     * @param currentPage 当前页号 ( 从 0 开始 )
     * @param pageSize 每页元素数量的大小 ( 大于 0 )
     * @return 该页显示的元素总数
     */
    private static Object[] pageCollectionFromPage(Collection collection, int currentPage, int pageSize) {
        if (isValidPageIndex(collection, currentPage, pageSize)) {
            int totalSize = collection.size();
            int beginIndex = currentPage * pageSize;
            int endIndex = Math.min(pageSize * (currentPage + 1), totalSize);

            Object[] retArray = new Object[endIndex - beginIndex];
            Object[] collectionArray = collection.toArray();
            int pageIterator = 0;

            for (int i = beginIndex; i < endIndex; i++) {
                retArray[pageIterator++] = collectionArray[i];
            }

            return retArray;
        }
        else {
            return null;
        }
    }

    private static Object[] pageCollectionFromItem(Collection collection, int currentItemIndex, int pageSize) {
        if (isValidItemIndex(collection, currentItemIndex, pageSize)) {
            int totalSize = collection.size();
            int beginIndex = currentItemIndex;
            int endIndex = endIndexByItemIndex(totalSize, currentItemIndex, pageSize);

            Object[] retArray = new Object[endIndex - beginIndex];
            Object[] collectionArray = collection.toArray();
            int pageIterator = 0;

            for (int i = beginIndex; i < endIndex; i++) {
                retArray[pageIterator++] = collectionArray[i];
            }

            return retArray;
        }
        else {
            return null;
        }
    }

    public static Object[] pageArrayFromPage(Object[] array, int currentPage, int pageSize) {
        int totalSize = array.length;
        int beginIndex = beginIndex(totalSize, currentPage, pageSize);
        int endIndex = endIndexByPageIndex(totalSize, currentPage, pageSize);

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


    /**
     * 根据 集合对象 和 当前页面编号 的大小判断是否可以分页
     * @param totalSize 当前元素集合总数
     * @param currentPage 当前页面编号
     * @param pageSize 页面大小
     * @return
     */
    public static boolean isValidPageIndex(int totalSize, int currentPage, int pageSize) {
        return currentPage >= 0
                && pageSize > 0
                && currentPage * pageSize < totalSize;
    }
    public static boolean isValidPageIndex(Collection collection, int currentPage, int pageSize) {
        return null != collection
                && isValidItemIndex(collection.size(), currentPage, pageSize);
    }


    /**
     * 根据 当前集合大小 和 当前元素编号 的大小判断是否可以分页
     * @param totalSize 当前元素总数
     * @param currentItem 当前页面编号
     * @param pageSize 页面大小
     * @return
     */
    public static boolean isValidItemIndex(int totalSize, int currentItem, int pageSize) {
        return currentItem >= 0
                && pageSize > 0
                && currentItem < totalSize;
    }
    public static boolean isValidItemIndex(Collection collection, int currentItem, int pageSize) {
        return null != collection
                && isValidItemIndex(collection.size(), currentItem, pageSize);
    }






//    public static HashSet pageSetFromItem(Set set, int currentItemIndex, int pageSize) {
//        Object[] setArray = pageCollectionFromItem(set, currentItemIndex, pageSize);
//
//        if (null != setArray) {
//            return new HashSet(Arrays.asList(pageCollectionFromItem(set, currentItemIndex, pageSize)));
//        }
//        else {
//            return new HashSet();
//        }
//    }




//    public static Set pageSetFromPage(Set set, int currentPage, int pageSize) {
//        Object[] setArray = pageCollectionFromPage(set, currentPage, pageSize);
//
//        if (null != setArray) {
//            return new HashSet(Arrays.asList(pageCollectionFromPage(set, currentPage, pageSize)));
//        }
//        else {
//            return new HashSet();
//        }
//    }
}
