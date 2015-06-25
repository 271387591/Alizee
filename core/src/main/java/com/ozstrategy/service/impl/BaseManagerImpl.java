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

    public T getByParam(Map<String, Object> map) {
        return baseDao().getByParam(map);
    }

    public T save(T obj) {
        return baseDao().save(obj);
    }

    public T saveOrUpdate(T obj) {
        return baseDao().saveOrUpdate(obj);
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

    public void batchSave(List<T> list) {
        for(T obj:list){
            baseDao().save(obj);
        }
    }

    public void batchUpdate(List<T> list) {
        for(T obj:list){
            baseDao().update(obj);
        }
    }

    public void batchDelete(List<T> list) {
        for(T obj:list){
            baseDao().delete(obj);
        }
    }

    public <D> List<D> findByNamedQuery(String queryName, Class<D> dClass) {
        return baseDao().findByNamedQuery(queryName, dClass);
    }

    public <D> List<D> findByNamedQuery(String queryName, Class<D> dClass, Map<String, Object> map) {
        return baseDao().findByNamedQuery(queryName, dClass,map);
    }

    public <D> List<D> findByNamedQueryPage(String queryName, Class<D> dClass, Map<String, Object> map, Integer start, Integer limit) {
        return baseDao().findByNamedQueryPage(queryName, dClass, map, start, limit);
    }

    public <D> D findByNamedQueryBean(String queryName, Class<D> dClass, Map<String, Object> map) {
        return baseDao().findByNamedQueryBean(queryName, dClass, map);
    }


}
