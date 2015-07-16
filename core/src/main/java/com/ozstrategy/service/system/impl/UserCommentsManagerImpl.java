package com.ozstrategy.service.system.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.system.UserCommentsDao;
import com.ozstrategy.model.system.UserComments;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.system.UserCommentsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("userCommentsManager")
public class UserCommentsManagerImpl extends BaseManagerImpl<UserComments> implements UserCommentsManager {
    @Autowired
    private UserCommentsDao userCommentsDao;

    @Override
    public BaseDao<UserComments> baseDao() {
        return userCommentsDao;
    }
}
