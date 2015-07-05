package com.ozstrategy.jdbc;

import com.ozstrategy.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihao1 on 6/7/15.
 */
public class EntityBuilder {
    private static Map<String,String> insert= Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String,String> update=Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String,String> delete=Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String,String> select=Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String,String> count=Collections.synchronizedMap(new HashMap<String, String>());
    private static Map<String, List<String>> columnMap = Collections.synchronizedMap(new HashMap<String, List<String>>());
    private static Map<String,Map<String,String>> nameQueries=Collections.synchronizedMap(new HashMap<String, Map<String, String>>());
    public static void build(Class cl){
        String table=getTableName(cl);
        caculationColumnList(cl);
        select.put(cl.getName(), buildSelect(table));
        count.put(cl.getName(),buildPageCount(table));
        insert.put(cl.getName(),buildInsertSql(cl, table));
        update.put(cl.getName(),buildUpdateSql(cl, table));
        delete.put(cl.getName(),buildDeleteSql(cl, table));
        nameQueries.put(cl.getName(),buildNameQuery(cl));
    }
    public static String select(Class cl){
        return select.get(cl.getName());
    }
    public static String insert(Class cl){
        return insert.get(cl.getName());
    }
    public static String update(Class cl){
        return update.get(cl.getName());
    }
    public static String delete(Class cl){
        return delete.get(cl.getName());
    }
    public static String count(Class cl){
        return count.get(cl.getName());
    }
    public static String nameQueries(Class cl,String queryName){
        Map<String,String> map=nameQueries.get(cl.getName());
        String sql=map.get(queryName);
        return sql;
//        return new Builder().nameQueries(cl, queryName);
    }
    public static Object[] nameQueriesArgs(Class cl,String queryName,Map<String,Object> params){
        return new Builder().nameQueriesArgs(cl, queryName, params);
    }
    static public void writeIdField(Object obj,Long id){
        new Builder().writeIdField(obj,id);
    }
    static public Object readIdField(Object obj){
        return new Builder().readIdField(obj);
    }


    public static   List<Object> buildArgs(Object obj){
        return new Builder().buildArgs(obj);
    }
    static private void caculationColumnList(Class cl) {

        if (columnMap.containsKey(cl.getName())) {
            return;
        }
        Field[] fields=cl.getDeclaredFields();
        List<String> list=new ArrayList<String>();
        for(Field field:fields){
            if(field.isAnnotationPresent(Id.class)){
                continue;
            }
            if(field.isAnnotationPresent(Transient.class)){
                continue;
            }
            list.add(field.getName());
        }
        columnMap.put(cl.getName(), list);
    }
    static private Map<String,String> buildNameQuery(Class cl){
        Map<String,String> map=new HashMap<String, String>();
        NamedQueries namedQueries = (NamedQueries) cl.getAnnotation(NamedQueries.class);
        if (namedQueries == null) {
            return map;
        } else {
            NamedQuery[] namedQueries1 = namedQueries.value();
            if(namedQueries1!=null && namedQueries1.length>0){
                for(NamedQuery namedQuery:namedQueries1){
                    map.put(namedQuery.name(),namedQuery.query());
                }
            }
            return map;
        }

    }
    private static String buildSelect(String table){
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("*");
        SqlBuilder.FROM(table);
        SqlBuilder.WHERE("1=1");
        String sql=SqlBuilder.SQL();
        return sql;
    }
    static private String buildPageCount(String table){
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("count(*)");
        SqlBuilder.FROM(table);
        SqlBuilder.WHERE("1=1");
        return SqlBuilder.SQL();
    }
    static private String buildInsertSql(Class cl,String table){
        List<String> list = columnMap.get(cl.getName());
        List<String> columnList=new ArrayList<String>();
        List<String> valueList=new ArrayList<String>();
        for(String column:list){
            columnList.add(column);
            valueList.add("?");
        }
        String columnNames= StringUtils.join(columnList.iterator(), ",");
        String values=StringUtils.join(valueList.iterator(),",");
        SqlBuilder.BEGIN();
        SqlBuilder.INSERT_INTO(table);
        SqlBuilder.VALUES(columnNames, values);
        String sql= SqlBuilder.SQL();
        return sql;
    }
    static private String buildUpdateSql(Class cl,String table){
        List<String> list = columnMap.get(cl.getName());
        String id=getIdColumn(cl);
        List<String> columnList=new ArrayList<String>();
        for(String column:list){
            columnList.add(column+"=?");
        }
        String columnNames=StringUtils.join(columnList.iterator(), ",");
        SqlBuilder.BEGIN();
        SqlBuilder.UPDATE(table);
        SqlBuilder.SET(columnNames);
        SqlBuilder.WHERE(id+"=?");
        String sql= SqlBuilder.SQL();
        return sql;
    }
    static private String buildDeleteSql(Class cl,String table){
        String id=getIdColumn(cl);
        SqlBuilder.BEGIN();
        SqlBuilder.DELETE_FROM(table);
        SqlBuilder.WHERE(id+"=?");
        String sql= SqlBuilder.SQL();
        return sql;
    }
    public  static String getTableName(Class cl) {
        Table table = (Table) cl.getAnnotation(Table.class);
        if (table == null) {
            return cl.getSimpleName().toLowerCase();
        } else {
            return table.name();
        }
    }
    public static String getIdColumn(Class cl){
        Field[] fields=cl.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(Id.class)){
                return field.getName();
            }
        }
        return "id";
    }
    private static class Builder{
        public   List<Object> buildArgs(Object obj){
            List<String> fields=columnMap.get(obj.getClass().getName());
            List<Object> list=new ArrayList<Object>();
            for(String field:fields){
                try {
                    Object o = FieldUtils.readDeclaredField(obj, field, true);
                    list.add(o);
                } catch (IllegalAccessException e) {
                }
            }
            return list;
        }
        public void writeIdField(Object obj,Long id){
            try {
                FieldUtils.writeDeclaredField(obj,getIdColumn(obj.getClass()),id,true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        public Object readIdField(Object obj){
            try {
                return FieldUtils.readDeclaredField(obj, getIdColumn(obj.getClass()), true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
        public Object[] nameQueriesArgs(Class cl,String queryName,Map<String,Object> params){
            Map<String,String> map=nameQueries.get(cl.getName());
            String sql=map.get(queryName);
            List<String> paramList=ParserHelper.parseQueryName(sql);
            List<Object> list=new ArrayList<Object>();
            if(paramList!=null && paramList.size()>0){
                for(String param:paramList){
                    list.add(params.get(param));
                }
            }
            return list.toArray();
        }
//        public String nameQueries(Class cl,String queryName){
//            Map<String,String> map=nameQueries.get(cl.getName());
//            String sql=map.get(queryName);
//            List<String> params=ParserHelper.parseQueryName(sql);
//            for(String param:params){
//                sql=sql.replaceAll(":"+param,"?");
//            }
//            return sql;
//        }
    }
}
