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
    T save(T obj);
    void update(T obj);
    void delete(T obj);
    void deleteById(Serializable id);


    List<T> findByNamedQuery(String queryName);
    List<T> findByNamedQuery(String queryName,Map<String,Object> map);
    List<T> findByNamedQueryPage(String queryName,Map<String,Object> map,Integer start,Integer limit);
    T findByNamedQueryBean(String queryName,Map<String,Object> map);
    Integer findByNamedQueryCount(String queryName,Map<String,Object> map);
}
