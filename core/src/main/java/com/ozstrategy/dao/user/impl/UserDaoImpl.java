package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.user.User;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-08.
*/
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

    public UserDaoImpl() {
    super(User.class);
    }

}