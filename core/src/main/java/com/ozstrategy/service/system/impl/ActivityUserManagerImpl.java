package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ActivityUserDao;
import com.ozstrategy.model.system.ActivityUser;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ActivityUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("activityUserManager")
public class ActivityUserManagerImpl extends BaseManagerImpl<ActivityUser> implements ActivityUserManager {
    @Autowired
    private ActivityUserDao activityUserDao;

    @Override
    public BaseDao<ActivityUser> baseDao() {
        return activityUserDao;
    }
}
