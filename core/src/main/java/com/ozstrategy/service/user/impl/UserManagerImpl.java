package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User> implements UserManager {
    @Autowired
    private UserDao userDao;

    @Override
    public BaseDao<User> baseDao() {
        return userDao;
    }
}
