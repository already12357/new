package com.zhq.util.JDBCUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL 查询条件类
 * 本质是将传入的数据转化为对应的 SQL 语句, 通过原生的 JDBC 执行
 * 流程为：
 * 1. 将传入的条件转化后存入到条件集合中 (eqConditionMap, gtConditionMap, ltConditionMap...)
 */
public class SqlCondition {
    // =============================================
    // 优化，根据 SqlCondition 获取传入的参数信息
    // =============================================
    // =============================================
    // 优化，添加表连接内容 ( left join .. on ....)
    // =============================================
    // =============================================
    // 优化，添加 exists, like, group by, order, limit, having 等条件
    // =============================================
    // =============================================
    // 优化，根据传入的 DataSource 对象调用
    // =============================================
    // =============================================
    // 优化，子查询语句的支持
    // =============================================
    // =============================================
    // 优化，添加分页查询效果
    // =============================================
    // =============================================
    // 优化，增加对数据库的不同写法
    // =============================================
    // =============================================
    // 优化，支持 Spring 注入
    // =============================================

    

    // 数据库类型
    private String dbType;

    // where 部分的所有条件内容集合
    private List<HashMap<String, List<String>>> whereConditionList;

    // '>', '<', '=', between, in条件存储在 HashMap 中
    // 根据 列名 --> 列名对应的表达式集合 存储
    private HashMap<String, List<String>> eqConditionMap;
    private HashMap<String, List<String>> gtConditionMap;
    private HashMap<String, List<String>> ltConditionMap;
    private HashMap<String, List<String>> betweenConditionMap;
    private HashMap<String, List<String>> inConditionMap;

    // 操作列对应的值
    // 操作列
    private List<String> opColumns;
    // 表格名称
    private List<String> opTables;
    // 操作中需要用到的值
    private List<Object> opValues;
    // 操作类型 ( 增删查改 )
    private String opType;

    // 使用 Get 获取对应的条件内容,  并使用懒加载
    private HashMap<String, List<String>> getLtConditionMap() {
        if (null == ltConditionMap) {
            ltConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(ltConditionMap);
        }
        return ltConditionMap;
    }
    private HashMap<String, List<String>> getGtConditionMap() {
        if (null == gtConditionMap) {
            gtConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(gtConditionMap);
        }
        return gtConditionMap;
    }
    private HashMap<String, List<String>> getEqConditionMap() {
        if (null == eqConditionMap) {
            eqConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(eqConditionMap);
        }
        return eqConditionMap;
    }
    private HashMap<String, List<String>> getInConditionMap() {
        if (null == inConditionMap) {
            inConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(inConditionMap);
        }
        return inConditionMap;
    }
    private HashMap<String, List<String>> getBetweenConditionMap() {
        if (null == betweenConditionMap) {
            betweenConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(betweenConditionMap);
        }
        return betweenConditionMap;
    }

    public SqlCondition() {
        opColumns = new ArrayList<String>();
        opTables = new ArrayList<String>();
        opValues = new ArrayList<Object>();

        opType = DBConstant.OP_SELECT;

        whereConditionList = new ArrayList<HashMap<String, List<String>>>();
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }
    public String getOpType() {
        return opType;
    }

    public String getDbType() {
        if (null == dbType || dbType.equals("")) {
            dbType = DBConstant.DB_MYSQL;
        }
        return dbType;
    }
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 链式调用快速的设置操作方式
     * @return
     */
    public SqlCondition toInsert() {
        opType = DBConstant.OP_INSERT;
        return this;
    }
    public SqlCondition toUpdate() {
        opType = DBConstant.OP_UPDATE;
        return this;
    }
    public SqlCondition toSelect() {
        opType = DBConstant.OP_SELECT;
        return this;
    }
    public SqlCondition toDelete() {
        opType = DBConstant.OP_DELETE;
        return this;
    }


    /**
     * 将 where 部分的判断条件转换为 SQL 语句后，添加到对应的集合中, 对每个列的每种判断条件用 List 存储 ( 支持链式写法 )
     *     如 : s_id 列的  s_id > 10, s_id > 20, s_id > 50 条件判断统一放在 gtConditionMap 对应的 List 中 )
     * @param columnName 列
     * @param columnValue 值
     * @param or 是否使用 or 拼接, 否则使用 and ( 通过前缀 * / # 区分 )
     */
    public SqlCondition gt(String columnName, Object columnValue, boolean or) {
        String sign = " > ";
        String gtStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(getGtConditionMap(), columnName, gtStr);
        return this;
    }
    public SqlCondition gt(String columnName, Object columnValue) {
        return gt(columnName, columnValue, false);
    }

    public SqlCondition eq(String columnName, Object columnValue, boolean or) {
        String sign = " = ";
        // 解析传入的内容，转换为表达式
        String eqStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(getEqConditionMap(), columnName, eqStr);
        return this;
    }
    public SqlCondition eq(String columnName, Object columnValue) {
        return eq(columnName, columnValue, false);
    }

    public SqlCondition lt(String columnName, Object columnValue, boolean or) {
        String sign = " < ";
        // 解析传入的内容，转换为表达式
        String ltStr = parseEquation(columnName, columnValue, sign, or);
        addConditionStr(getLtConditionMap(), columnName, ltStr);
        return this;
    }
    public SqlCondition lt(String columnName, Object columnValue) {
        return lt(columnName, columnValue, false);
    }

    public SqlCondition between(String columnName, Object bottom, Object top, boolean or) {
        // 将传入内容解析为字符串
        String betweenStr = parseBetween(columnName, bottom, top, or);
        // 将解析后的 Between 语句加入到集合中
        addConditionStr(getBetweenConditionMap(), columnName, betweenStr);
        return this;
    }
    public SqlCondition between(String columnName, Object bottom, Object top) {
        return between(columnName, bottom, top, false);
    }

//    public SqlCondition like();
//    public SqlCondition exists();


    public SqlCondition in(String columnName, List<Object> rangeList, boolean or) {
        String inStr = parseIn(columnName, rangeList, or);
        addConditionStr(getInConditionMap(), columnName, inStr);
        return this;
    }
    public SqlCondition in(String columnName, List<Object> rangeList) {
        return in(columnName, rangeList, false);
    }


    /**
     * 添加操作涉及的 列 表 值, 通常用在 非 select 情况下使用 ( 支持链式写法 )
     * @param columnName 列名称 / 表名称
     */
    public SqlCondition columns(String columnName, String...columnNames) {
        if (!opColumns.contains(columnName)) {
            opColumns.add(columnName);
        }

        for (String extraColumn : columnNames) {
            if (!opColumns.contains(columnName)) {
                opColumns.add(columnName);
            }
        }
        return this;
    }
    public SqlCondition tables(String tableName, String...tableNames) {
        if (!opTables.contains(tableName)) {
            opTables.add(tableName);
        }

        for (String extraTable : tableNames) {
            if (!opTables.contains(extraTable)) {
                opTables.add(extraTable);
            }
        }

        return this;
    }
    public SqlCondition values(Object value, Object...valueContents) {
        opValues.add(value);
        for (Object extraValue : valueContents) {
            opValues.add(extraValue);
        }
        return this;
    }

//    /**
//     * 连接表时的添加字段
//     * @param from
//     * @return
//     */
//    public SqlCondition join(String from) {
//
//
//        return this;
//    }


    /**
     * 获取 where 部分中与对应列相关的判断内容
     *     如: 获取 where 中涉及 stu_1 列的所有 between 语句 (getBetweenStr("stu_1"))
     * @param columnName 列名称
     * @return
     */
    public String getEqStr(String columnName) {
        return getConditionStr(columnName, eqConditionMap);
    }

    public String getGtStr(String columnName) {
        return getConditionStr(columnName, gtConditionMap);
    }

    public String getLtStr(String columnName) {
        return getConditionStr(columnName, ltConditionMap);
    }

    public String getBetweenStr(String columnName) {
        return getConditionStr(columnName, betweenConditionMap);
    }

    public String getInStr(String columnName) {
        return getConditionStr(columnName, inConditionMap);
    }


    /**
     * 解析传入的判断参数，将其转换为 SQL 字符串
     * @param columnName 列名称
     * @param columnValue 列值
     * @param or 表示是采取 or 拼接, 还是采取 and 拼接 ( 前缀区分 '*'/'#' )
     * @return 处理后的 SQL 语句
     */
    private String parseEquation(String columnName, Object columnValue, String sign, boolean or) {
        StringBuilder parsedEq = new StringBuilder("");

        if (or) {
            parsedEq.append("*");
        }
        else {
            parsedEq.append("#");
        }

        parsedEq.append("(")
                .append(columnName)
                .append(sign)
                .append(DBFormatter.formatObjToStr(columnValue))
                .append(")");

        return parsedEq.toString();
    }

    private String parseBetween(String columnName, Object bottom, Object top, boolean or) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (or) {
            parsedBetween.append("*");
        }
        else {
            parsedBetween.append("#");
        }

        parsedBetween.append("(")
                .append(columnName)
                .append(" BETWEEN ")
                .append(DBFormatter.formatObjToStr(bottom))
                .append(" AND ")
                .append(DBFormatter.formatObjToStr(top))
                .append(")");

        return parsedBetween.toString().trim();
    }

    private String parseIn(String columnName, List<Object> rangeList, boolean or) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (or) {
            parsedBetween.append("*");
        }
        else {
            parsedBetween.append("#");
        }

        parsedBetween.append("(")
                .append(columnName)
                .append(" IN ")
                .append("(");

        for (Object range : rangeList) {
            parsedBetween.append(DBFormatter.formatObjToStr(range)).append(",");
        }

        parsedBetween.append(")")
                .append(")")
                .deleteCharAt(parsedBetween.length() - 3);

        return parsedBetween.toString().trim();
    }

    /**
     * 将转换后的 判断 SQL 依据列存放到对应的条件集合中
     * @param conditionMap 需要传入的条件集合
     * @param columnName 对应的条件列名
     * @param condStr 条件语句
     */
    private void addConditionStr(HashMap<String, List<String>> conditionMap, String columnName, String condStr) {
        if (null == conditionMap.get(columnName)) {
            List<String> eqList = new ArrayList<>();
            eqList.add(condStr);
            conditionMap.put(columnName, eqList);
        }
        else {
            conditionMap.get(columnName).add(condStr);
        }
    }


    /**
     * 获取对应列的条件语句
     * @param columnName 查询的列名
     * @param conditionMap 用于存放条件语句的集合
     * @return
     */
    private String getConditionStr(String columnName, HashMap<String, List<String>> conditionMap) {
        List<String> condStrList = conditionMap.get(columnName);
        StringBuilder findSql = new StringBuilder("");
        int startIndex = 0;

        if (null != condStrList && !condStrList.isEmpty()) {
            for (int i = 0; i < condStrList.size(); i++) {
                String equation = condStrList.get(i);

                if (equation.charAt(0) == '*') {
                    equation = " or " + equation.substring(2, equation.length() - 1);
                }
                else {
                    equation = " and " + equation.substring(2, equation.length() - 1);
                }

                findSql.append(equation);
            }
            findSql.delete(0, 1);
            startIndex = findSql.indexOf(" ") + 1;

            return findSql.substring(startIndex);
        }

        return "";
    }


    /**
     * 根据内部的配置信息生成对应的 Sql 语句, 通过操作类型分类 ( 增删查改 )
     * @return
     */
    public String generateSql() {
        switch (opType) {
            case DBConstant.OP_UPDATE:
                return generateUpdateSql();

            case DBConstant.OP_INSERT:
                return generateInsertSql();

            case DBConstant.OP_DELETE:
                return generateDeleteSql();

            default:
                return generateSelectSql();
        }
    }

    /**
     * 根据内部配置生成不同的 Sql 语句 ( select, update, delete, insert )
     * @return
     */
    private String generateInsertSql() {
        if (!opTables.isEmpty()) {
            StringBuilder insertBuilder = new StringBuilder(opType);

            insertBuilder.append(" INTO ")
                    .append(opTables.get(0));
            if (null != opColumns && !opColumns.isEmpty()) {
                insertBuilder.append("(").
                        append(columnsInSql()).
                        append(")");
            }
            insertBuilder.append(" ")
                    .append(valuesInSql());

            return insertBuilder.toString();
        }

        return "";
    }

    private String generateSelectSql() {
        StringBuilder selectBuilder = new StringBuilder(opType);
        // 逐次拼接对应的内容
        selectBuilder.append(" ")
                .append(columnsInSql())
                .append(" ")
                .append(fromInSql())
                .append(" ")
                .append(whereInSql());
        return selectBuilder.toString();
    }

    private String generateDeleteSql() {
        if (!opTables.isEmpty()) {
            StringBuilder deleteBuilder = new StringBuilder(opType);
            deleteBuilder.append(" ")
                    .append(fromInSql())
                    .append(" ")
                    .append(whereInSql());

            return deleteBuilder.toString();
        }

        return "";
    }

    private String generateUpdateSql() {
        if (!opColumns.isEmpty() && !opTables.isEmpty()) {
            StringBuilder updateBuilder = new StringBuilder(opType);

            updateBuilder.append(" ")
                    .append(tableInSql())
                    .append(" ")
                    .append(setInSql())
                    .append(" ")
                    .append(whereInSql());

            return updateBuilder.toString();
        }

        return "";
    }


    /**
     * 根据内部配置生成 SQL 整体语句的不同部分 ( where, from, having 等 )
     * @return
     */
    private String columnsInSql() {
        if (!opColumns.isEmpty()) {
            StringBuilder columnsStr = new StringBuilder("");

            for (String column : opColumns) {
                columnsStr.append(column + ",");
            }
            columnsStr.deleteCharAt(columnsStr.length() - 1);
            return columnsStr.toString().trim();
        }

        return "";
    }

    private String fromInSql() {
        if (!opTables.isEmpty()) {
            StringBuilder fromStr = new StringBuilder("FROM ");
            fromStr.append(tableInSql());
            return fromStr.toString().trim();
        }

        return "";
    }

    private String tableInSql() {
        if (!opTables.isEmpty()) {
            StringBuilder fromStr = new StringBuilder("");

            for (String table : opTables) {
                fromStr.append(table + ",");
            }
            fromStr.deleteCharAt(fromStr.length() - 1);
            return fromStr.toString().trim();
        }

        return "";
    }

    private String whereInSql() {
        if (!whereConditionList.isEmpty()) {
            StringBuilder whereStr = new StringBuilder("WHERE ");

            for (HashMap<String, List<String>> part : whereConditionList) {
                for (Map.Entry<String, List<String>> entry : part.entrySet()) {
                    String columnName = entry.getKey();
                    whereStr.append(getConditionStr(columnName, part));
                }
                whereStr.append(" and ");
            }

            whereStr.delete(whereStr.length() - 5, whereStr.length());
            return whereStr.toString().trim();
        }

        return "";
    }

    private String valuesInSql() {
        if (!opValues.isEmpty()) {
            StringBuilder valuesStr = new StringBuilder("VALUES").append("(");

            for (Object value : opValues) {
                valuesStr.append(DBFormatter.formatObjToStr(value)).append(",");
            }

            valuesStr.append(")");
            valuesStr.deleteCharAt(valuesStr.length() - 2);
            return valuesStr.toString().trim();
        }

        return "";
    }

    private String setInSql() {
        int setCount = Math.min(opColumns.size(), opValues.size());

        if (setCount > 0) {
            StringBuilder setStr = new StringBuilder("SET ");
            for (int i = 0; i < setCount; i++) {
                String expression = opColumns.get(i).concat(" = ")
                        .concat(DBFormatter.formatObjToStr(opValues.get(i)));
                setStr.append(expression).append(",");
            }

            setStr.deleteCharAt(setStr.length() - 1);
            return setStr.toString().trim();
        }

        return "";
    }



}
