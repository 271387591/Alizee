package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.ActivityUserDao;
import com.ozstrategy.model.system.ActivityUser;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-25.
*/
@Repository("activityUserDao")
public class ActivityUserDaoImpl extends BaseDaoImpl<ActivityUser> implements ActivityUserDao{

    public ActivityUserDaoImpl() {
    super(ActivityUser.class);
    }

}