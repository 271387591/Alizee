package com.ozstrategy.service.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.service.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/8/15.
 */
public abstract class BaseManagerImpl<T> implements BaseManager<T> {
//    protected BaseDao<T> baseDao;
//
//    public BaseManagerImpl(BaseDao<T> baseDao) {
//        this.baseDao=baseDao;
//    }
    public abstract BaseDao<T> baseDao();

    public List<T> list(Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().listPage(map, start, limit);
    }

    public Integer count(Map<String, Object> map) {
        return baseDao().listPageCount(map);
    }

    public List<T> listAll() {
        return baseDao().listAll();
    }

    public List<T> listAll(Map<String, Object> map) {
        return baseDao().listAll(map);
    }

    public T get(Serializable id) {
        return baseDao().get(id);
    }

    public T save(T obj) {
        return baseDao().save(obj);
    }

    public void update(T obj) {
        baseDao().update(obj);

    }

    public void delete(T obj) {
        baseDao().delete(obj);

    }

    public void deleteById(Serializable id) {
        baseDao().deleteById(id);

    }

    public List<T> findByNamedQuery(String queryName) {
        return baseDao().findByNamedQuery(queryName);
    }

    public List<T> findByNamedQuery(String queryName, Map<String, Object> map) {
        return baseDao().findByNamedQuery(queryName,map);
    }

    public List<T> findByNamedQueryPage(String queryName, Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().findByNamedQueryPage(queryName, map, start, limit);
    }

    public T findByNamedQueryBean(String queryName, Map<String, Object> map) {
        return baseDao().findByNamedQueryBean(queryName, map);
    }

    public Integer findByNamedQueryCount(String queryName, Map<String, Object> map) {
        return baseDao().findByNamedQueryCount(queryName, map);
    }
}
