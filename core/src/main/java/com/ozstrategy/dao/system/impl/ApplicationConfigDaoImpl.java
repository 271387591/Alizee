package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.model.system.ApplicationConfig;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
* Created by lihao1 on 2015-06-10.
*/
@Repository("applicationConfigDao")
public class ApplicationConfigDaoImpl extends BaseDaoImpl<ApplicationConfig> implements ApplicationConfigDao{

    public ApplicationConfigDaoImpl() {
    super(ApplicationConfig.class);
    }

    public String getValue(String key) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_systemKey_EQ",key);
        ApplicationConfig config=getByParam(map);
        if(config!=null){
            return config.getSystemValue();
        }
        return null;
    }
}