package com.ozstrategy.service.user.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.MerchantDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.exception.UserNotAuthException;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.user.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User> implements UserManager {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MerchantDao merchantDao;


    @Override
    public BaseDao<User> baseDao() {
        return userDao;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_username_EQ",username);
        map.put("Q_enabled_EQ",Boolean.TRUE);
        User user=getByParam(map);
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }

        map=new HashMap<String, Object>();
        map.put("userId",user.getId());
        List<Role> roles = userDao.findByNamedQuery("getRoles", Role.class,map);
        user.getRoles().clear();
        user.getRoles().addAll(roles);
        Long parentId = user.getParentId();
        if(parentId!=null){
            User parent=userDao.get(parentId);
            if(!parent.getEnabled()){
                throw new UserNotAuthException("user not enable");
            }
        }

        return user;
    }

    public User getUserByUsername(String username) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_username_EQ",username);
        map.put("Q_enabled_EQ",Boolean.TRUE);
        return getByParam(map);
    }

    public User saveUser(User user) {
        boolean save=false;
        if (user.getId() == null) {
            user.setCreateDate(new Date());
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
            save=true;
        }
        user.setLastUpdateDate(new Date());
        if(save){
            userDao.save(user);
        }else{
            userDao.update(user);
        }
        userDao.deleteRoles(user);
        Set<Role> roles=user.getRoles();
        if(roles!=null && roles.size()>0){
            for(Role role:roles){
                userDao.saveRoles(user,role);
            }
        }
        return user;
    }

    public boolean changePassword(User user,String newPassword, String oldPassword, boolean admin) {
        String pwd=user.getPassword();
        if(admin){
            user.setPassword(passwordEncoder.encodePassword(newPassword, null));
            userDao.update(user);
            return true;
        }else{
            String encode=passwordEncoder.encodePassword(oldPassword, null);
            if(StringUtils.equals(user.getPassword(),encode)){
                return false;
            }
            user.setPassword(passwordEncoder.encodePassword(newPassword, null));
            userDao.update(user);
        }
        return true;
    }

    public void batchDeleteUser(List<User> users) {
        for(User user:users){
            if(user.getEnabled()){
                user.setEnabled(Boolean.FALSE);
            }else{
                user.setEnabled(Boolean.TRUE);
            }
            userDao.update(user);
        }
    }

    public void deleteUser(User user) {
        userDao.deleteRoles(user);
        userDao.delete(user);
    }

    public void updateCridits(User user) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_userId_EQ",user.getId());
        Merchant merchant=merchantDao.getByParam(map);
        if(merchant!=null){
            merchant.setCredits(user.getCredits());
            merchantDao.update(merchant);
        }
        userDao.update(user);
    }

}
