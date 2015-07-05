package com.ozstrategy.service.system;

import com.ozstrategy.model.system.Activity;
import com.ozstrategy.service.BaseManager;

import java.util.List;

/**
* Created by lihao1 on 2015-06-25.
*/
public interface ActivityManager extends BaseManager<Activity> {
    void deleteAct(List<Activity> activities);
}
