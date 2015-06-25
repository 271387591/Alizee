package com.ozstrategy.dao.user;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;

/**
* Created by lihao1 on 2015-06-08.
*/
public interface UserDao extends BaseDao<User> {
    void deleteRoles(User user);
    void saveRoles(User user,Role role);
}