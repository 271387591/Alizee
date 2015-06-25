package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ApplicationConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("applicationConfigManager")
public class ApplicationConfigManagerImpl extends BaseManagerImpl<ApplicationConfig> implements ApplicationConfigManager {
    @Autowired
    private ApplicationConfigDao applicationConfigDao;

    @Override
    public BaseDao<ApplicationConfig> baseDao() {
        return applicationConfigDao;
    }

    public ApplicationConfig getConfig(String key) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_systemKey_EQ",key);
        ApplicationConfig config=applicationConfigDao.getByParam(map);
        return config;
    }
}
