package com.ozstrategy.dao.system.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.system.UserCommentsDao;
import com.ozstrategy.model.system.UserComments;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-07-14.
*/
@Repository("userCommentsDao")
public class UserCommentsDaoImpl extends BaseDaoImpl<UserComments> implements UserCommentsDao{

    public UserCommentsDaoImpl() {
    super(UserComments.class);
    }

}