package com.template._1.util;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.ref.Ref;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义实现的 word 模板数据源实现类,
 * 用于给 word 模板中提供数据，作为 word 模板的数据源 ( 实现 IMailMergeDataSource 接口 )
 */
public class TemplateMailMergeDataSource implements IMailMergeDataSource {
    /**
     * 内部的渲染数据,
     * 使用 List<Map> 使用在嵌套的环境下
     */
    private List<Map<String, Object>> renderData;
    // 内部用于标示当前渲染到第几个区域
    private int index = -1;
    // 用于存储对应的区域名称
    private String tableName;


    /**
     * 蛋羹数据集的构造，不存在模板嵌套的情况
     * @param data
     * @param tableName 模板前缀
     */
    public TemplateMailMergeDataSource(Map<String, Object> data, String tableName) {
        if (null == renderData) {
            renderData = new ArrayList<Map<String, Object>>();
            renderData.add(data);
        }

        this.tableName = tableName;
        this.index = -1;
    }

    /**
     * 多个数据集的构造，存在模板嵌套
     */
    public TemplateMailMergeDataSource(List<Map<String, Object>> renderData, String tableName) {
        this.renderData = renderData;
        this.tableName = tableName;
        this.index = -1;
    }



    /**
     * 用于在区域编辑域合并时，作为区域的名称前缀
     * 如这里的 RegionName 就作为一个区域名称
     * <<TableStart:RegionName>>
     *     <<>>
     *         <<>>
     * <<TableEnd:RegionName>>
     */
    @Override
    public String getTableName() throws Exception {
        return this.tableName;
    }

    /**
     * 用于确定是否需要重复渲染下一个 区域 / 前缀 ( Region )
     */
    @Override
    public boolean moveNext() throws Exception {
        index++;

        /**
         * 当超出当前的区域总数时，不再渲染
         */
        if (index >= this.getCount()) {
            return false;
        }
        else {
            return true;
        }
    }


    /**
     * 用来给域中的数据做替换, 给替换域提供数据
     * @param field 域的名称
     * @param ref 对应替换域的值
     */
    @Override
    public boolean getValue(String field, Ref<Object> ref) throws Exception {
        /**
         * 当超出区域渲染范围时
         */
        if (index < 0 || index >= this.getCount()) {
            return false;
        }

        if (ref != null) {
            // 设置渲染的数据
            ref.set(renderData.get(index).get(field));
        }
        else {
            return false;
        }

        return true;
    }


    /**
     * 当涉及模板中存在嵌套的情况时，需要用到子数据源，
     * 如：在 ProjectInfo 编辑域中签嵌套其他的属性
     *
     *     <<ProjectInfo:ProjectName>>
     *     <<ProjectInfo:CreateDate>>
     *     <<ProjectInfo:Company>>
     *         等...
     */
    @Override
    public IMailMergeDataSource getChildDataSource(String s) throws Exception {
        return null;
    }


    /**
     * 获取渲染区域的总数，即前缀的总数
     */
    public int getCount() {
        return this.renderData.size();
    }
}
