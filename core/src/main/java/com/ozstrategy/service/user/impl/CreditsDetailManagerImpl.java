package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.user.CreditsDetailDao;
import com.ozstrategy.model.user.CreditsDetail;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.CreditsDetailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("creditsDetailManager")
public class CreditsDetailManagerImpl extends BaseManagerImpl<CreditsDetail> implements CreditsDetailManager {
    @Autowired
    private CreditsDetailDao creditsDetailDao;

    @Override
    public BaseDao<CreditsDetail> baseDao() {
        return creditsDetailDao;
    }
}
