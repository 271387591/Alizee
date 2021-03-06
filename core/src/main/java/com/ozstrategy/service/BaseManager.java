package com.ozstrategy.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/8/15.
 */
public interface BaseManager<T> {
    List<T> list(Map<String,Object> map,Integer start,Integer limit);
    Integer count(Map<String,Object> map);
    List<T> listAll();
    List<T> listAll(Map<String,Object> map);
    T get(Serializable id);
    T getByParam(Map<String,Object> map);
    T save(T obj);
    void update(T obj);
    void delete(T obj);
    void deleteById(Serializable id);

    void batchSave(List<T> list);
    void batchUpdate(List<T> list);
    void batchDelete(List<T> list);


    <D> List<D> findByNamedQuery(String queryName,Class<D> dClass);
    <D> List<D> findByNamedQuery(String queryName,Class<D> dClass,Map<String,Object> map);
    <D> List<D> findByNamedQueryPage(String queryName,Class<D> dClass,Map<String,Object> map,Integer start,Integer limit);
    <D> D findByNamedQueryBean(String queryName,Class<D> dClass,Map<String,Object> map);
}
