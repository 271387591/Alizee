package com.ozstrategy.dao.user.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.user.Role;
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

    public void deleteRoles(User user) {
        String sql="delete from t_userrole where userId=?";
        jdbcTemplate.update(sql,user.getId());
    }

    public void saveRoles(User user, Role role) {
        String sql="insert into t_userrole(userId,roleId) values(?,?)";
        jdbcTemplate.update(sql,user.getId(),role.getId());
    }
}