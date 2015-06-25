package com.ozstrategy.service.user;

import com.ozstrategy.model.user.User;
import com.ozstrategy.service.BaseManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
* Created by lihao1 on 2015-06-08.
*/
public interface UserManager extends BaseManager<User>,UserDetailsService {
    User getUserByUsername(String username);
    User saveUser(User user);
    boolean changePassword(User user,String newPassword,String oldPassword,boolean admin);
    void batchDeleteUser(List<User> users);
}
