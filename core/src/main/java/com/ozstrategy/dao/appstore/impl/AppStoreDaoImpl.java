package com.ozstrategy.dao.appstore.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.appstore.AppStoreDao;
import com.ozstrategy.model.appstore.AppStore;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-30.
*/
@Repository("appStoreDao")
public class AppStoreDaoImpl extends BaseDaoImpl<AppStore> implements AppStoreDao{

    public AppStoreDaoImpl() {
    super(AppStore.class);
    }

}