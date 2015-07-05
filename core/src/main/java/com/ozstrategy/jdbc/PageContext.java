package com.ozstrategy.jdbc;

/**
 * Created by lihao1 on 6/29/15.
 */
public class PageContext {
    private String sql;
    public PageContext(String sql){
        this.sql=sql;
    }
    public String page(Integer start,Integer limit){
        this.sql+=" limit "+start+","+limit;
        return this.sql;
    }
}
