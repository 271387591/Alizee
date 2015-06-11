package com.ozstrategy.dao.test;

import com.ozstrategy.dao.user.RoleDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.service.user.UserManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 6/7/15.
 */
public class UserDaoTest extends BaseManagerTestCase {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private Md5PasswordEncoder passwordEncoder;




    @Test
    public void testPasswordEncoder(){
        String pwd="tomcat";
        String encode = passwordEncoder.encodePassword(pwd, null);


        System.out.println("ddd===="+encode);
    }

    @Test
    public void testGet(){
        UserDetails user=userManager.loadUserByUsername("admin");
        Role role = roleManager.get(1L);
        System.out.println("ddd");
        System.out.println("ddd");
        System.out.println("ddd");
        System.out.println("ddd");
        System.out.println("ddd");
    }

//    @Test
//    @Rollback(value = false)
//    public void testInsert(){
//        User user=new User();
//        user.setName("11");
//        userDao.save(user);
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//    }
//
//    @Test
//    @Rollback(value = false)
//    public void testUpdate(){
//        User user=userDao.get(1L);
//        user.setName("11");
//        userDao.update(user);
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//    }
//    @Test
//    public void testSelect(){
//        List<User> users=userDao.listAll();
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("Q_id_EQ", 3);
//        users=userDao.listAll(map);
//        Integer count=userDao.listPageCount(map);
//
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//    }
//    @Test
//    public void testNameQuery(){
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("id",3);
//        List<User> users=userDao.findByNamedQuery("kkk",map);
//        Integer count=userDao.findByNamedQueryCount("kkk", map);
//
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//        System.out.println("ddd");
//    }



}
