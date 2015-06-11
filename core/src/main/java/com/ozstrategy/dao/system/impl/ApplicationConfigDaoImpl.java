package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.model.system.ApplicationConfig;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-10.
*/
@Repository("applicationConfigDao")
public class ApplicationConfigDaoImpl extends BaseDaoImpl<ApplicationConfig> implements ApplicationConfigDao{

    public ApplicationConfigDaoImpl() {
    super(ApplicationConfig.class);
    }

}