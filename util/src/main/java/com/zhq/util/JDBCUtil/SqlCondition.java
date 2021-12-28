package com.zhq.util.JDBCUtil;

import com.zhq.util.ResourceUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL 查询条件类
 * 本质是将传入的数据转化为对应的 SQL 语句, 通过原生的 JDBC 执行
 * 流程为：
 * 1. 将传入的条件转化后存入到条件集合 (eqConditionMap, gtConditionMap, ltConditionMap...), 操作相关的 表集合，值集合，列集合
 * 2. 解析集合中的内容，将其通过部分转化的形式 ( where, from 等部分 )， 转化后拼接成完整的 SQL 字符串
 * 3. 通过解析解析拼接的字符串，来调用执行对应的 SQL 操作
 * @author Eddie Zhang
 */
public class SqlCondition {
    // =============================================
    // 优化，根据 SqlCondition 获取传入的参数信息
    // =============================================
    // =============================================
    // 优化，添加表连接内容 ( left join .. on ....)
    // =============================================
    // =============================================
    // 优化，添加 exists, group by, order, limit, having 等条件
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
    // =============================================
    // 优化，Json 执行格式返回
    // =============================================
    /**
     * 连接池连接释放问题........
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     */


    // where 部分的所有条件内容集合
    private List<HashMap<String, List<String>>> whereConditionList;

    // '>', '<', '=', between, in条件存储在 HashMap 中
    // 根据 列名 --> 列名对应的表达式集合 存储
    private HashMap<String, List<String>> eqConditionMap;
    private HashMap<String, List<String>> gtConditionMap;
    private HashMap<String, List<String>> ltConditionMap;
    private HashMap<String, List<String>> betweenConditionMap;
    private HashMap<String, List<String>> inConditionMap;
    private HashMap<String, List<String>> likeConditionMap;
    private HashMap<String, List<String>> existsConditionMap;
    // 表连接的内容
    private HashMap<String, List<String>> joinConditionMap;
    // 分页信息存储 ( 注意需要指定数据库类型  )
    // pageIndex <--> 0 当前页号 
    // pageSize <--> 10 当前页的大小
    private HashMap<String, Integer> pageConditionMap;

    // 操作列对应的值
    // 当前操作列
    private List<String> opColumns;
    // 当前操作表
    private List<String> opTables;
    // 当前操作值
    private List<Object> opValues;
    // 操作类型 ( 增删查改 )
    private String opType;
    // 数据库类型
    private String dbType;


    // 使用 Get 获取对应的条件内容,  并使用懒加载
    // clearCondition 清楚相关的数据内容
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
    private HashMap<String, List<String>> getLikeConditionMap() {
        if (null == likeConditionMap) {
            likeConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(likeConditionMap);
        }
        return likeConditionMap;
    }
    private HashMap<String, List<String>> getExistsConditionMap() {
        if (null == existsConditionMap) {
            existsConditionMap = new HashMap<String, List<String>>();
            whereConditionList.add(existsConditionMap);
        }
        return existsConditionMap;
    }

    // 用于存储对应的表连接内容 ( 被连接表的名称 - 相关的表达式  )
    private HashMap<String, List<String>> getJoinConditionMap() {
        if (null == joinConditionMap) {
            joinConditionMap = new HashMap<String, List<String>>();
        }
        return joinConditionMap;
    }
    public HashMap<String, Integer> getPageConditionMap() {
        if (null == pageConditionMap) {
            pageConditionMap = new HashMap<String, Integer>();
        }
        return pageConditionMap;
    }

    public SqlCondition() {
        opColumns = new ArrayList<String>();
        opTables = new ArrayList<String>();
        opValues = new ArrayList<Object>();

        opType = DBConstant.SQL_SELECT;

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
     * 链式调用快速切换操作类型, 注意切换时会清空数据
     * @return
     */
    public SqlCondition toInsert() {
        clearCondition();
        opType = DBConstant.SQL_INSERT;
        return this;
    }
    public SqlCondition toUpdate() {
        clearCondition();
        opType = DBConstant.SQL_UPDATE;
        return this;
    }
    public SqlCondition toSelect() {
        clearCondition();
        opType = DBConstant.SQL_SELECT;
        return this;
    }
    public SqlCondition toDelete() {
        clearCondition();
        opType = DBConstant.SQL_DELETE;
        return this;
    }


    /**
     * 增强链式调用时的语句可读性, 没有实际处理作用
     */
    public SqlCondition where() {
        return this;
    }
    public SqlCondition or() {
        return this;
    }
    public SqlCondition and() {
        return this;
    }

    /**
     * delete 语句组, 可以用于生成对应的 delete 语句, 然后由外部的数据库连接源调用
     * @return
     */
    public SqlCondition delete_from(String tableName, String...tableNames) {
        toDelete();
        tables(tableName, tableNames);
        return this;
    }

    /**
     * select 语句组, 可以用于生成对应的 select 语句
     * 如 :
     *      SQL : select * from course_1 where c_id between 9 and 14
     *                    and c_id=(select c2.c_id from course_1 c2 where c2.c_id=9)
     *
     *      Java :
     *      SqlCondition sql = new SqlCondition();
     *      sql.select_col("*").from("course_1").where().between("c_id", 9, 14);
     *      // 通过对象的数据源来执行对应的 SqlCondition 对象
     *      sql.executedBy(customDataSource);
     * @return
     */
    public SqlCondition select_col(String columnName, String...columnNames) {
        toSelect();
        columns(columnName, columnNames);

        return this;
    }
    public SqlCondition from(String tableName, String...tableNames) {
        tables(tableName, tableNames);
        return this;
    }

    public SqlCondition left_join(String joinTable, Object joinOn) {
        if (null != opTables && !opTables.isEmpty()) {
            join(opTables.get(opTables.size() - 1), DBConstant.SQL_LEFT, joinTable, joinOn);
        }
        return this;
    }
    public SqlCondition left_join(String joinTable) {
        return left_join(joinTable, "");
    }

    public SqlCondition right_join(String joinTable, Object joinOn) {
        if (null != opTables && !opTables.isEmpty()) {
            join(opTables.get(opTables.size() - 1), DBConstant.SQL_RIGHT, joinTable, joinOn);
        }
        return this;
    }
    public SqlCondition inner_join(String joinTable, Object joinOn) {
        if (null != opTables && !opTables.isEmpty()) {
            join(opTables.get(opTables.size() - 1), DBConstant.SQL_INNER, joinTable, joinOn);
        }
        return this;
    }


    /**
     * insert 语句组, 可以用于生成对应的 insert 语句
     * @return
     */
    public SqlCondition insert_into(String tableName) {
        toInsert();
        tables(tableName);
        return this;
    }


    /**
     * update 语句组, 可以用于生成对应的 update 语句
     */
    public SqlCondition update_table(String tableName, String...tableNames) {
        toUpdate();
        tables(tableName, tableNames);
        return this;
    }
    public SqlCondition set_col(String columnName, String...columnNames) {
        columns(columnName, columnNames);
        return this;
    }

//    // 多表打印
//    public SqlCondition insert_all() {
//
//
//        return this;
//    }

    /**
     * 将 where 部分的判断条件转换为 SQL 语句后，添加到对应的集合中, 对每个列的每种判断条件用 List 存储 ( 支持链式写法 )
     *     如 : s_id 列的  s_id > 10, s_id > 20, s_id > 50 条件判断统一放在 gtConditionMap 对应的 List 中 )
     * @param columnName 列
     * @param columnValue 值
     * @param append 拼接方式, 使用 and / or ( 通过前缀 * / # 区分 ), 对应常量 DBConstant.SQL_AND, DBConstant.SQL_OR
     */
    public SqlCondition gt(String append, String columnName, Object columnValue) {
        String sign = " > ";
        String gtStr = parseEquation(append, columnName, columnValue, sign);
        addConditionStr(getGtConditionMap(), columnName, gtStr);
        return this;
    }
    public SqlCondition gt(String columnName, Object columnValue) {
        return gt(DBConstant.SQL_AND, columnName, columnValue);
    }

    public SqlCondition eq(String append, String columnName, Object columnValue) {
        String sign = " = ";
        // 解析传入的内容，转换为表达式
        String eqStr = parseEquation(append, columnName, columnValue, sign);
        addConditionStr(getEqConditionMap(), columnName, eqStr);
        return this;
    }
    public SqlCondition eq(String columnName, Object columnValue) {
        return eq(DBConstant.SQL_AND, columnName, columnValue);
    }

    public SqlCondition lt(String append, String columnName, Object columnValue) {
        String sign = " < ";
        // 解析传入的内容，转换为表达式
        String ltStr = parseEquation(append, columnName, columnValue, sign);
        addConditionStr(getLtConditionMap(), columnName, ltStr);
        return this;
    }
    public SqlCondition lt(String columnName, Object columnValue) {
        return lt(DBConstant.SQL_AND, columnName, columnValue);
    }

    public SqlCondition between(String append, String columnName, Object bottom, Object top) {
        // 将传入内容解析为字符串
        String betweenStr = parseBetween(append, columnName, bottom, top);
        // 将解析后的 Between 语句加入到集合中
        addConditionStr(getBetweenConditionMap(), columnName, betweenStr);
        return this;
    }
    public SqlCondition between(String columnName, Object bottom, Object top) {
        return between(DBConstant.SQL_AND, columnName, bottom, top);
    }

    public SqlCondition like(String append, String columnName, String columnValue) {
        String likeStr = parseEquation(append, columnName, columnValue, " LIKE ");
        addConditionStr(getLikeConditionMap(), columnName, likeStr);
        return this;
    }
    public SqlCondition like(String columnName, String columnValue) {
        return like(DBConstant.SQL_AND, columnName, columnValue);
    }

    public SqlCondition exists(String append, String columnName, Object columnValue) {
        String existsStr = parseExists(append, columnName, columnValue);
        addConditionStr(getExistsConditionMap(), columnName, existsStr);
        return this;
    }
    public SqlCondition exists(String columnName, Object columnValue) {
        return exists(DBConstant.SQL_AND, columnName, columnValue);
    }

    public SqlCondition in(String append, String columnName, List<Object> rangeList) {
        String inStr = parseIn(append, columnName, rangeList);
        addConditionStr(getInConditionMap(), columnName, inStr);
        return this;
    }
    public SqlCondition in(String columnName, List<Object> rangeList) {
        return in(DBConstant.SQL_AND, columnName, rangeList);
    }

    public SqlCondition join(String opTable, String joinType, String joinTable, Object on) {
        String joinStr = parseJoin(opTable, joinType, joinTable, on);
        addConditionStr(getJoinConditionMap(), opTable, joinStr);
        return this;
    }
    public SqlCondition join(String opTable, String joinTable, Object on) {
        return join(opTable, DBConstant.SQL_LEFT, joinTable, on);
    }

    
    /**
     * 添加操作涉及的 列 表 值, 会清空原有数据，通常用在 非 select 情况下使用 ( 支持链式写法 )
     * @param columnName 列名称 / 表名称
     */
    public SqlCondition columns(String columnName, String...columnNames) {
        opColumns.clear();
        opColumns.add(columnName);

        for (String extraColumn : columnNames) {
            if (!opColumns.contains(extraColumn)) {
                opColumns.add(extraColumn);
            }
        }

        return this;
    }
    public SqlCondition tables(String tableName, String...tableNames) {
        opTables.clear();
        opTables.add(tableName);

        for (String extraTable : tableNames) {
            if (!opTables.contains(extraTable)) {
                opTables.add(extraTable);
            }
        }

        return this;
    }
    public SqlCondition values(Object value, Object...valueContents) {
        opValues.clear();
        opValues.add(value);
        for (Object extraValue : valueContents) {
            opValues.add(extraValue);
        }
        return this;
    }

    /**
     * 用于分页
     * @param pageIndex 当前页号
     * @param pageSize 当前页的大小
     */
    public SqlCondition page(int pageIndex, int pageSize) {
        if (pageIndex >= 0 && pageSize > 0) {
            getPageConditionMap().put("pageIndex", Integer.valueOf(pageIndex));
            getPageConditionMap().put("pageSize", Integer.valueOf(pageSize));
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
     * @param append 拼接方式, 使用 and / or ( 通过前缀 * / # 区分 ), 对应常量 DBConstant.SQL_AND, DBConstant.SQL_OR
     * @param columnName 列名称
     * @param columnValue 列值
     * @param sign 连接两者的操作符 ( '=', ' > ', ' < ', 'like' ... )
     * @return 处理后的 SQL 语句
     */
    private String parseEquation(String append, String columnName, Object columnValue, String sign) {
        StringBuilder parsedEq = new StringBuilder("");

        if (append.equalsIgnoreCase(DBConstant.SQL_OR)) {
            parsedEq.append("*");
        }
        else {
            parsedEq.append("#");
        }

        parsedEq.append("(")
                .append(columnName)
                .append(sign)
                .append(DBFormatter.formatObjToStr(columnValue, true, false))
                .append(")");

        return parsedEq.toString();
    }

    private String parseBetween(String append, String columnName, Object bottom, Object top) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (append.equalsIgnoreCase(DBConstant.SQL_OR)) {
            parsedBetween.append("*");
        }
        else {
            parsedBetween.append("#");
        }

        parsedBetween.append("(")
                .append(columnName)
                .append(" BETWEEN ")
                .append(DBFormatter.formatObjToStr(bottom, true, false))
                .append(" AND ")
                .append(DBFormatter.formatObjToStr(top, true, false))
                .append(")");

        return parsedBetween.toString().trim();
    }

    private String parseIn(String append, String columnName, List<Object> rangeList) {
        StringBuilder parsedBetween = new StringBuilder("");

        if (append.equalsIgnoreCase(DBConstant.SQL_OR)) {
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
            parsedBetween.append(DBFormatter.formatObjToStr(range, true, false)).append(",");
        }

        parsedBetween.append(")")
                .append(")")
                .deleteCharAt(parsedBetween.length() - 3);

        return parsedBetween.toString().trim();
    }

    private String parseExists(String append, String columnName, Object columnValue) {
        StringBuilder parsedExists = new StringBuilder();

        if (append.equalsIgnoreCase(DBConstant.SQL_OR)) {
            parsedExists.append("*");
        }
        else {
            parsedExists.append("#");
        }

        parsedExists.append("(")
                .append(columnName)
                .append(" EXISTS ")
                .append(")");

        return parsedExists.toString().trim();
    }

    private String parseJoin(String opTable, String joinType, String joinTable, Object on) {
        if ((null != opTable && !opTable.isEmpty()) ||
                (null != joinTable && !joinTable.isEmpty())) {
            // 每次都要连接最近添加进去的表
            StringBuilder parsedJoin = new StringBuilder(opTable);
            String onStr = DBFormatter.formatObjToStr(on, false, false);

            // opTable L(joinTable, .....)
            switch (joinType) {
                case DBConstant.SQL_RIGHT:
                    parsedJoin.append(" R");
                    break;
                case DBConstant.SQL_INNER:
                    parsedJoin.append(" I");
                    break;
                default:
                    parsedJoin.append(" L");
                    break;
            }

            parsedJoin.append("(")
                    .append(joinTable);

            if (null != onStr && !onStr.isEmpty()) {
                parsedJoin.append(",").append(onStr);
            }

            parsedJoin.append(")");

            return parsedJoin.toString().trim();
        }

        return "";
    }


    /**
     * 将转换后的 判断 SQL 依据列存放到对应的条件集合中
     * @param conditionMap 需要传入的条件集合
     * @param columnName 对应的条件列名
     * @param condStr 条件语句
     */
    private void addConditionStr(HashMap<String, List<String>> conditionMap, String columnName, String condStr) {
        if ((null != condStr && !condStr.isEmpty()) ||
                (null != columnName && !columnName.isEmpty())) {
            if (null == conditionMap.get(columnName)) {
                List<String> eqList = new ArrayList<>();
                eqList.add(condStr);
                conditionMap.put(columnName, eqList);
            }
            else {
                conditionMap.get(columnName).add(condStr);
            }
        }
    }


    /**
     * 获取对应列的条件语句
     * @param columnName 查询的列名
     * @param conditionMap 用于存放条件语句的集合
     * @param append 在返回语句时, 是否携带连接词 ( and, or)
     * @return
     */
    private String getConditionStr(String columnName, HashMap<String, List<String>> conditionMap, boolean append) {
        if (null != conditionMap && !conditionMap.isEmpty()) {
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

                // 不需要添加连接词 ( and, or )
                if (!append) {
                    findSql.delete(0, 1);
                    startIndex = findSql.indexOf(" ") + 1;
                }

                return findSql.substring(startIndex);
            }
        }

        return "";
    }
    private String getConditionStr(String columnName, HashMap<String, List<String>> conditionMap) {
        if (null != conditionMap && !conditionMap.isEmpty()) {
            return getConditionStr(columnName, conditionMap, false);
        }

        return "";
    }

    private String getJoinStr(String opTable, HashMap<String, List<String>> conditionMap) {
        if (null != conditionMap && !conditionMap.isEmpty()) {
            StringBuilder joinSql = new StringBuilder("");
            List<String> opJoinStrList = conditionMap.get(opTable);

            // opTable L(joinTable, .....)
            if (null != opJoinStrList && !opJoinStrList.isEmpty()) {
                for (String onJoinStr : opJoinStrList) {
                    StringBuilder tableJoinStr = new StringBuilder(onJoinStr);

                    int typeCharIndex = onJoinStr.indexOf("(") - 1;
                    Character typeChar = onJoinStr.charAt(typeCharIndex);
                    switch (typeChar) {
                        case 'R':
                            tableJoinStr.replace(typeCharIndex, typeCharIndex + 1, DBConstant.SQL_RIGHT);
                            break;

                        case 'I':
                            tableJoinStr.replace(typeCharIndex, typeCharIndex + 1, DBConstant.SQL_INNER);
                            break;

                        default:
                            tableJoinStr.replace(typeCharIndex, typeCharIndex + 1, DBConstant.SQL_LEFT);
                            break;
                    }

                    tableJoinStr.replace(tableJoinStr.indexOf("("), tableJoinStr.indexOf("(") + 1, DBFormatter.gapStrWithBlank(DBConstant.SQL_JOIN));

                    if (tableJoinStr.indexOf(",") != -1) {
                        tableJoinStr.replace(tableJoinStr.indexOf(","), tableJoinStr.indexOf(",") + 1, DBFormatter.gapStrWithBlank(DBConstant.SQL_ON));
                    }

                    tableJoinStr.deleteCharAt(tableJoinStr.lastIndexOf(")"));

                    joinSql.append(tableJoinStr);
                    joinSql.append(",");
                }

                joinSql.deleteCharAt(joinSql.length() - 1);
            }

            return joinSql.toString().trim();
        }

        return "";
    }

    /**
     * 根据内部的配置信息生成对应的 Sql 语句, 通过操作类型分类 ( 增删查改 )
     * @return
     */
    public String generateSql() {
        switch (opType) {
            case DBConstant.SQL_UPDATE:
                return generateUpdateSql();

            case DBConstant.SQL_INSERT:
                return generateInsertSql();

            case DBConstant.SQL_DELETE:
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

    /**
     * 分页解析，未完成 !!!!!!!!!!!!!!!!!!!!!!!!
     * 解析 pageConditionMap 数据结构，拼接对应的分页内容, 修改 DBFormatter.pageQuerySql
     * @return
     */
    private String generateSelectSql() {
        StringBuilder selectBuilder = new StringBuilder(opType);

        // 拼接生成查询内容的 SQL
        selectBuilder.append(" ")
                .append(columnsInSql())
                .append(" ")
                .append(fromInSql())
                .append(" ")
                .append(whereInSql());


        // 有分页的情况，在分页子查询外层的基础上，生成对应的 sql
        // select * from (实际的  sql) limit....( 分页条件 )
        if (null != pageConditionMap) {
            Integer pageIndex = getPageConditionMap().get("pageIndex");
            Integer pageSize = getPageConditionMap().get("pageSize");
            return DBFormatter.pageQuerySql(pageIndex, pageSize, getDbType(), selectBuilder.toString());
        }

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
    public String columnsInSql() {
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

    public String fromInSql() {
        if (!opTables.isEmpty()) {
            StringBuilder fromStr = new StringBuilder("FROM ");
            if (null != joinConditionMap) {
                fromStr.append(tableJoinInSql());
            }
            else {
                fromStr.append(tableInSql());
            }

            return fromStr.toString().trim();
        }

        return "";
    }

    public String tableInSql() {
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

    public String tableJoinInSql() {
        // opTable L(joinTable, .....)
        if (!opTables.isEmpty()) {
            StringBuilder tableJoinStr = new StringBuilder("");

            for (String opTable : opTables) {
                List<String> opTableJoins = joinConditionMap.get(opTable);
                tableJoinStr.append(getJoinStr(opTable, getJoinConditionMap()));
                tableJoinStr.append(",");
            }
            tableJoinStr.deleteCharAt(tableJoinStr.length() - 1);

            return tableJoinStr.toString().trim();
        }

        return "";
    }

    public String whereInSql() {
        if (!whereConditionList.isEmpty()) {
            int startIndex = 0;
            StringBuilder whereStr = new StringBuilder("WHERE ");
            StringBuilder conditionPart = new StringBuilder("");

            for (HashMap<String, List<String>> columnCondition : whereConditionList) {
                for (Map.Entry<String, List<String>> conditionEntry : columnCondition.entrySet()) {
                    String columnName = conditionEntry.getKey();
                    conditionPart.append(getConditionStr(columnName, columnCondition, true));
                }
            }

            conditionPart.delete(0, 1);
            startIndex = conditionPart.indexOf(" ") + 1;
            whereStr.append(conditionPart.substring(startIndex));
            return whereStr.toString().trim();
        }

        return "";
    }

    public String valuesInSql() {
        if (!opValues.isEmpty()) {
            StringBuilder valuesStr = new StringBuilder("VALUES").append("(");

            for (Object value : opValues) {
                valuesStr.append(DBFormatter.formatObjToStr(value, true, false)).append(",");
            }

            valuesStr.append(")");
            valuesStr.deleteCharAt(valuesStr.length() - 2);
            return valuesStr.toString().trim();
        }

        return "";
    }

    public String setInSql() {
        int setCount = Math.min(opColumns.size(), opValues.size());

        if (setCount > 0) {
            StringBuilder setStr = new StringBuilder("SET ");
            for (int i = 0; i < setCount; i++) {
                String expression = opColumns.get(i).concat(" = ")
                        .concat(DBFormatter.formatObjToStr(opValues.get(i), true, false));
                setStr.append(expression).append(",");
            }

            setStr.deleteCharAt(setStr.length() - 1);
            return setStr.toString().trim();
        }

        return "";
    }


    public Object executedBy(DataSource dataSource) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = dataSource.getConnection();
            return executedBy(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object executedBy(Connection connection) {
//        try {
//            PreparedStatement ps = connection.prepareStatement(generateSql());
//            switch (opType) {
//                case DBConstant.SQL_DELETE:
//                case DBConstant.SQL_INSERT:
//                    return ps.execute();
//
//                case DBConstant.SQL_UPDATE:
//                    return ps.executeUpdate();
//
//                case DBConstant.SQL_SELECT:
//                    return ps.executeQuery();
//            }
//
//            return null;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }

        try {
            PreparedStatement ps = connection.prepareStatement(generateSql());
            Object result = null;
            switch (opType) {
                case DBConstant.SQL_DELETE:
                case DBConstant.SQL_INSERT:
                    result = ps.execute();
                    break;

                case DBConstant.SQL_UPDATE:
                    result = ps.executeUpdate();
                    break;

                case DBConstant.SQL_SELECT:
                    result = ps.executeQuery();
                    break;
            }

            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            ResourceUtil.closeResources(connection);
        }
    }



    /**
     * 清空该 SqlCondition 中存储的所有条件数据, 用于重置 SqlCondition
     */
    public void clearCondition() {
        opValues.clear();
        opColumns.clear();
        opTables.clear();
        whereConditionList.clear();
        gtConditionMap = null;
        ltConditionMap = null;
        eqConditionMap = null;
        betweenConditionMap = null;
        inConditionMap = null;
        likeConditionMap = null;
        existsConditionMap = null;
        joinConditionMap = null;
        pageConditionMap = null;
    }
}
