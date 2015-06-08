package com.ozstrategy.dao.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.jdbc.EntityBuilder;
import com.ozstrategy.jdbc.QueryField;
import com.ozstrategy.jdbc.QuerySearch;
import com.ozstrategy.jdbc.SqlBuilder;
import com.ozstrategy.model.BaseEntity;
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

    public BaseDaoImpl(Class clazz) {
        this.clazz = clazz;
        EntityBuilder.build(clazz);
    }


    public List<T> listAll() throws RuntimeException {
        String sql = EntityBuilder.select(this.clazz);
        return (List<T>) jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public List<T> listAll(Map<String, Object> params) {
        return listPage(params,0,Integer.MAX_VALUE);
    }


    public List<T> listPage(Map<String, Object> params, Integer start, Integer limit) {
        String sql=EntityBuilder.select(this.clazz);
        QuerySearch queryField=new QuerySearch(sql,params);
        Object[] args = queryField.getArgs();
        sql=queryField.pageSql(start, limit);
        return (List<T>) jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public List<T> findByNamedQuery(String queryName) {
        return findByNamedQuery(queryName,new HashMap<String, Object>());
    }

    public List<T> findByNamedQuery(String queryName, Map<String, Object> params) {
        return findByNamedQueryPage(queryName,params,0,Integer.MAX_VALUE);
    }

    public List<T> findByNamedQueryPage(String queryName, Map<String, Object> params, Integer start, Integer limit) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        Object[] args = EntityBuilder.nameQueriesArgs(sql, params);
        return (List<T>) jdbcTemplate.query(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public T findByNamedQueryBean(String queryName, Map<String, Object> params) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        Object[] args = EntityBuilder.nameQueriesArgs(sql,params);
        return (T) jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public Integer findByNamedQueryCount(String queryName, Map<String, Object> params) {
        String sql=EntityBuilder.nameQueries(this.clazz,queryName);
        Object[] args = EntityBuilder.nameQueriesArgs(sql,params);
        return jdbcTemplate.queryForObject(sql, Integer.class, args);
    }

    public Integer listPageCount(Map<String, Object> params) {
        String sql=EntityBuilder.count(this.clazz);
        QuerySearch queryField=new QuerySearch(sql,params);
        Object[] args = queryField.getArgs();
        sql=queryField.sql();
        return jdbcTemplate.queryForObject(sql, Integer.class, args);
    }

    public T save(T entity) {
        final String sql=EntityBuilder.insert(this.clazz);
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

    public void update(T entity) {
        final String sql=EntityBuilder.update(this.clazz);
        List<Object> list=EntityBuilder.buildArgs(entity);
        list.add(EntityBuilder.readIdField(entity));
        final Object[] args=list.toArray();
        jdbcTemplate.update(sql, args);
    }

    public void delete(T entity) {
        final String sql=EntityBuilder.delete(this.clazz);
        final Object[] args = new Object[]{EntityBuilder.readIdField(entity)};
        jdbcTemplate.update(sql, args);
    }

    public T get(Serializable id) {
        String sql=EntityBuilder.select(this.clazz);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_"+EntityBuilder.getIdColumn(this.clazz)+"_EQ",id);
        QuerySearch querySearch=new QuerySearch(sql,map);
        sql=querySearch.sql();
        Object[] args=querySearch.getArgs();
        return (T) jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(this.clazz));
    }

    public void deleteById(Serializable id) {
        final String sql=EntityBuilder.delete(this.clazz);
        jdbcTemplate.update(sql, new Object[]{id});
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
