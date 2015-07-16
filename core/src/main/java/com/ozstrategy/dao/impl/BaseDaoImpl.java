package com.ozstrategy.dao.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.jdbc.*;
import com.ozstrategy.model.BaseEntity;
import com.ozstrategy.model.user.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 4/30/15.
 */
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
    protected JdbcTemplate jdbcTemplate;
    Class clazz;
    protected transient Log log= LogFactory.getLog(BaseDao.class);

    public BaseDaoImpl(Class clazz) {
        this.clazz = clazz;
        EntityBuilder.build(clazz);
    }


    public List<T> listAll() throws RuntimeException {
        String sql = EntityBuilder.select(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        return (List<T>) jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public List<T> listAll(Map<String, Object> params) {
        return listPage(params,0,Integer.MAX_VALUE);
    }


    public List<T> listPage(Map<String, Object> params, Integer start, Integer limit) {
        String sql=EntityBuilder.select(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        QuerySearch queryField=new QuerySearch(sql,params);
        queryField.addSort(EntityBuilder.getIdColumn(this.clazz),"DESC");
        Object[] args = queryField.getArgs();
        sql=queryField.pageSql(start, limit);
        return (List<T>) jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public <D> List<D> findByNamedQuery(String queryName,Class<D> dClass) {
        return findByNamedQuery(queryName,dClass,new HashMap<String, Object>());
    }

    public <D> List<D> findByNamedQuery(String queryName,Class<D> dClass, Map<String, Object> params) {
        return findByNamedQueryPage(queryName,dClass,params,0,Integer.MAX_VALUE);
    }

    public <D> List<D> findByNamedQueryPage(String queryName,Class<D> dClass, Map<String, Object> params, Integer start, Integer limit) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        QuerySearch querySearch=new QuerySearch(sql,params);
//        querySearch.addSort(EntityBuilder.getIdColumn(this.clazz),"DESC");
        sql=querySearch.pageSql(start, limit);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args = querySearch.getArgs();
        return (List<D>) jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(dClass));
    }

    public <D> D findByNamedQueryBean(String queryName,Class<D> dClass, Map<String, Object> params) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        QuerySearch querySearch=new QuerySearch(sql,params);
        querySearch.getSortMap().clear();
        sql=querySearch.sql();
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args = querySearch.getArgs();
        return (D) jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(dClass));
    }

    public <D> D findByNamedQueryClass(String queryName, Class<D> dClass, Map<String, Object> params) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        QuerySearch querySearch=new QuerySearch(sql,params);
        querySearch.getSortMap().clear();
        sql=querySearch.sql();
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args = querySearch.getArgs();
        return (D) jdbcTemplate.queryForObject(sql, dClass,args);
    }

    public Map<String, Object> findByNamedQueryMap(String queryName, Map<String, Object> params) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        QuerySearch querySearch=new QuerySearch(sql,params);
        querySearch.getSortMap().clear();
        sql=querySearch.sql();
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args = querySearch.getArgs();
        return jdbcTemplate.queryForMap(sql,args);
    }

    public List<Map<String, Object>> findByNamedQuery(String queryName, Map<String, Object> params) {
        return findByNamedQuery(queryName,params,0,Integer.MAX_VALUE);
    }

    public List<Map<String, Object>> findByNamedQuery(String queryName, Map<String, Object> params, Integer start, Integer limit) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        QuerySearch querySearch=new QuerySearch(sql,params);
//        querySearch.addSort(EntityBuilder.getIdColumn(this.clazz),"DESC");
        sql=querySearch.pageSql(start, limit);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args = querySearch.getArgs();
        return jdbcTemplate.queryForList(sql,args);
    }

    public Integer listPageCount(Map<String, Object> params) {
        String sql=EntityBuilder.count(this.clazz);

        QuerySearch queryField=new QuerySearch(sql,params);
        queryField.getSortMap().clear();
        Object[] args = queryField.getArgs();
        sql=queryField.sql();
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        return jdbcTemplate.queryForObject(sql, Integer.class, args);
    }

    public T save(T entity) {
        final String sql=EntityBuilder.insert(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        final Object[] args=EntityBuilder.buildArgs(entity).toArray();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                new ArgumentPreparedStatementSetter(args).setValues(ps);
                return ps;
            }
        }, keyHolder);
        Long generatedId = keyHolder.getKey().longValue();
        EntityBuilder.writeIdField(entity,generatedId);
        return entity;
    }

    public T saveOrUpdate(T entity) {
        Object id  = EntityBuilder.readIdField(entity);
        if(id==null){
            return save(entity);
        }else{
            update(entity);
            return entity;
        }
    }

    public int update(T entity) {
        final String sql=EntityBuilder.update(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        List<Object> list=EntityBuilder.buildArgs(entity);
        list.add(EntityBuilder.readIdField(entity));
        final Object[] args=list.toArray();
        return jdbcTemplate.update(sql, args);
    }

    public int delete(T entity) {
        final String sql=EntityBuilder.delete(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        final Object[] args = new Object[]{EntityBuilder.readIdField(entity)};
        return jdbcTemplate.update(sql, args);
    }

    public T get(Serializable id) {
        if(id==null)return null;
        String sql=EntityBuilder.select(this.clazz);

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_"+EntityBuilder.getIdColumn(this.clazz)+"_EQ",id);
        QuerySearch querySearch=new QuerySearch(sql,map);
        sql=querySearch.sql();
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        Object[] args=querySearch.getArgs();
        return (T) jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public T getByParam(Map<String, Object> map) {
        List<T> list=listPage(map,0,1);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public int deleteById(Serializable id) {
        final String sql=EntityBuilder.delete(this.clazz);
        if(log.isDebugEnabled()){
            log.debug("sql:====>"+sql.replaceAll("\\n"," "));
        }
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    public int deleteByParam(Map<String, Object> map) {
        SqlBuilder.BEGIN();
        SqlBuilder.DELETE_FROM(EntityBuilder.getTableName(this.clazz));
        SqlBuilder.WHERE("1=1");
        String sql=SqlBuilder.SQL();
        QuerySearch querySearch=new QuerySearch(sql,map);
        sql=querySearch.sql();
        Object[] args=querySearch.getArgs();
        return jdbcTemplate.update(sql,args);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
