package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.CreditsDetailDao;
import com.ozstrategy.model.user.CreditsDetail;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-08-25.
*/
@Repository("creditsDetailDao")
public class CreditsDetailDaoImpl extends BaseDaoImpl<CreditsDetail> implements CreditsDetailDao{

    public CreditsDetailDaoImpl() {
    super(CreditsDetail.class);
    }

}