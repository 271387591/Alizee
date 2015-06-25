package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.ActivityDao;
import com.ozstrategy.model.system.Activity;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-25.
*/
@Repository("activityDao")
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao{

    public ActivityDaoImpl() {
    super(Activity.class);
    }

}