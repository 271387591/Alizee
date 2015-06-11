package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ApplicationConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
