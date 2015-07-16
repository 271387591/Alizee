package com.ozstrategy.dao;

import com.ozstrategy.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 4/30/15.
 */
public interface BaseDao<T> {
    List<T> listAll()  throws RuntimeException;
    List<T> listAll(Map<String,Object> params);
    List<T> listPage(Map<String,Object> params,Integer start,Integer limit);
    <D> List<D> findByNamedQuery(String queryName,Class<D> dClass);
    <D> List<D> findByNamedQuery(String queryName,Class<D> dClass,Map<String,Object> map);
    <D> List<D> findByNamedQueryPage(String queryName,Class<D> dClass,Map<String,Object> map,Integer start,Integer limit);
    <D> D findByNamedQueryBean(String queryName,Class<D> dClass,Map<String,Object> map);
    <D> D findByNamedQueryClass(String queryName,Class<D> dClass,Map<String,Object> map);
    Map<String,Object> findByNamedQueryMap(String queryName,Map<String,Object> map);
    List<Map<String,Object>> findByNamedQuery(String queryName,Map<String,Object> map);
    List<Map<String,Object>> findByNamedQuery(String queryName,Map<String,Object> map,Integer start,Integer limit);

    Integer listPageCount(Map<String,Object> params);
    T save(T entity);
    T saveOrUpdate(T entity);
    int update(T entity);
    int delete(T entity);
    T get(Serializable id);
    T getByParam(Map<String,Object> map);
    int deleteById(Serializable id);
    int deleteByParam(Map<String,Object> map);
}
