package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.FoodDao;
import com.ozstrategy.model.system.Food;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-21.
*/
@Repository("foodDao")
public class FoodDaoImpl extends BaseDaoImpl<Food> implements FoodDao{

    public FoodDaoImpl() {
    super(Food.class);
    }

}