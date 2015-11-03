package com.ozstrategy.service.appstore.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.appstore.AppStoreDao;
import com.ozstrategy.model.appstore.AppStore;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.appstore.AppStoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("appStoreManager")
public class AppStoreManagerImpl extends BaseManagerImpl<AppStore> implements AppStoreManager {
    @Autowired
    private AppStoreDao appStoreDao;

    @Override
    public BaseDao<AppStore> baseDao() {
        return appStoreDao;
    }

    public void saveApp(AppStore appStore) {
        if(appStore.getEnabled()){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_enabled_EQ",1);
            map.put("Q_plat_EQ",appStore.getPlat());
            AppStore appStoren=appStoreDao.getByParam(map);
            if(appStoren!=null){
                appStoren.setEnabled(Boolean.FALSE);
                appStoreDao.update(appStoren);
            }
        }
        appStoreDao.saveOrUpdate(appStore);
    }
}
