package com.ozstrategy.dao.recharge.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.recharge.RechargeDao;
import com.ozstrategy.model.recharge.Recharge;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-02.
*/
@Repository("rechargeDao")
public class RechargeDaoImpl extends BaseDaoImpl<Recharge> implements RechargeDao{

    public RechargeDaoImpl() {
    super(Recharge.class);
    }

}