package com.ozstrategy.dao.recharge.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.recharge.ConsumeDetailDao;
import com.ozstrategy.model.recharge.ConsumeDetail;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-02.
*/
@Repository("consumeDetailDao")
public class ConsumeDetailDaoImpl extends BaseDaoImpl<ConsumeDetail> implements ConsumeDetailDao{

    public ConsumeDetailDaoImpl() {
    super(ConsumeDetail.class);
    }

}