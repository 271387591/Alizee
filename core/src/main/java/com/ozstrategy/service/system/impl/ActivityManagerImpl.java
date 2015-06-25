package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ActivityDao;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("activityManager")
public class ActivityManagerImpl extends BaseManagerImpl<Activity> implements ActivityManager {
    @Autowired
    private ActivityDao activityDao;

    @Override
    public BaseDao<Activity> baseDao() {
        return activityDao;
    }
}
