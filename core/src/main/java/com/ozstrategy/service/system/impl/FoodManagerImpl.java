package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.FoodDao;
import com.ozstrategy.model.system.Food;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.FoodManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("foodManager")
public class FoodManagerImpl extends BaseManagerImpl<Food> implements FoodManager {
    @Autowired
    private FoodDao foodDao;

    @Override
    public BaseDao<Food> baseDao() {
        return foodDao;
    }
}
