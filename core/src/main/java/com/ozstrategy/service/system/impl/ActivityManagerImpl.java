package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.ActivityDao;
import com.ozstrategy.dao.system.ActivityUserDao;
import com.ozstrategy.model.system.Activity;
import com.ozstrategy.model.system.ActivityUser;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.ActivityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("activityManager")
public class ActivityManagerImpl extends BaseManagerImpl<Activity> implements ActivityManager {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityUserDao activityUserDao;


    @Override
    public BaseDao<Activity> baseDao() {
        return activityDao;
    }

    public void deleteAct(List<Activity> activities) {
        for(Activity activity:activities){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_activityId_EQ",activity.getId());
            activityUserDao.deleteByParam(map);
            activityDao.delete(activity);
        }
    }
}
