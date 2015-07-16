package com.ozstrategy.jdbc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lihao
 * Date: 7/17/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 * 简单的单表搜索查询工具
 */
public class QuerySearch {
    private List<QueryField> commands=new ArrayList<QueryField>();
    private List<QueryField> orCommands=new ArrayList<QueryField>();
    private Map<String,String> sortMap = new HashMap<String, String>();
    private String sql;
    private List<Object> values = new ArrayList<Object>();
    public QuerySearch(String baseHql,Map<String,Object> map){
        this.sql=baseHql;
        List<String> alias=ParserHelper.parseQueryName(this.sql);
        if(alias!=null && alias.size()>0){
            for(String alia:alias){
                this.sql=this.sql.replaceAll(":"+alia,"?");
                this.values.add(map.get(alia));
            }
        }
        this.initField(map);
    }
    public QuerySearch addSort(String sort,String dir){
        if(StringUtils.isNotEmpty(sort)){
            if(StringUtils.isNotEmpty(dir)){
                this.sortMap.put(sort,dir);
            }else{
                this.sortMap.put(sort,"ASC");
            }
        }
        return this;
    }
    public String sql(){
        String add_baseHql="",or_baseHql="";
        for(QueryField field:commands){
            String getHql=field.getPartHql();
            add_baseHql+=getHql;
        }
        for(QueryField field:orCommands){
            String getHql=field.getPartOrHql();
            or_baseHql+=getHql;
        }
        if(or_baseHql!=""){
            or_baseHql=or_baseHql.replaceFirst("or","");
            or_baseHql=" and ("+or_baseHql+")";
        }
        sql+=add_baseHql;
        sql+=or_baseHql;
        if(sortMap.size()>0){
            sql+=" order by ";
            for(Iterator<Map.Entry<String,String>> it=sortMap.entrySet().iterator();it.hasNext();){
                Map.Entry<String,String> entry=it.next();
                String key=entry.getKey();
                String value=entry.getValue();
                sql+= key+" "+value+",";
            }
            sql=sql.substring(0,sql.length()-1);
        }
        return sql;
    }
    public String pageSql(Integer start,Integer limit){
        sql();
        if(start!=null && limit!=null){
            sql=new PageContext(this.sql).page(start,limit);
        }
        return sql;
    }
    public Map<String,String> getSortMap(){
        return this.sortMap;
    }
    public Object[] getArgs(){
        return values.toArray();
    }
    public boolean emptySort(){
        return sortMap.isEmpty();
    }
    public static void main(String[] args){
        Map<String,Object> map1=new HashMap<String, Object>();
        map1.put("Q_name_LK","d");
        map1.put("Q_name1_LK","d1");
        map1.put("Q_name2_LK_OR","d2");
        map1.put("Q_name3_LK_OR","d3");
        QuerySearch search=new QuerySearch("from user where 1=1",map1);
//        search.initField(map1);
        String hql=search.sql();
        Object[] ge=search.getArgs();
        System.out.println(hql);

    }
    private void initField(Map<String,Object> map){
        if(map!=null){
            for(Iterator<Map.Entry<String,Object>> it=map.entrySet().iterator();it.hasNext();){
                Map.Entry<String,Object> entry=it.next();
                String key=entry.getKey();
                Object value=entry.getValue();
                if(key.startsWith("Q_")){
                    addFilter(key,value);
                    sortFilter(key,value);
                }

            }
            for(Iterator<Map.Entry<String,Object>> it=map.entrySet().iterator();it.hasNext();){
                Map.Entry<String,Object> entry=it.next();
                String key=entry.getKey();
                Object value=entry.getValue();
                if(key.startsWith("Q_")){
                    orFilter(key, value);
                    sortFilter(key,value);
                }
            }
        }
    }
    private void sortFilter(String property, Object value){
        if(value==null || StringUtils.isEmpty(value.toString())){
            return;
        }
        String as[] = property.split("[_]");
        if(as!=null && as.length==2){
            sortMap.put(as[1],value.toString());
        }
    }

    private void addFilter(String property, Object value) {
        if(value==null || StringUtils.isEmpty(value.toString())){
            return;
        }
        String as[] = property.split("[_]");
        /*Q_title_LK*/
        /*Q_u.title_LK*/
        if (as != null && as.length == 3) {
            QueryField queryField = new QueryField(as[1], as[2]);
            commands.add(queryField);
            if(as[2].equals("LK")){
                values.add("%"+value+"%");
            }else{
                values.add(value);
            }
        }
    }
    private void orFilter(String property, Object value) {
        if(value==null || StringUtils.isEmpty(value.toString())){
            return;
        }
        String as[] = property.split("[_]");
        /*Q_title_LK*/
        /*Q_u.title_LK*/
        if(as != null && as.length == 4){/*Q_title_LK_OR*/
            QueryField queryField = new QueryField(as[1], as[2]);
            orCommands.add(queryField);
            if(as[2].equals("LK")){
                values.add("%"+value+"%");
            }else{
                values.add(value);
            }
        }
    }



}
