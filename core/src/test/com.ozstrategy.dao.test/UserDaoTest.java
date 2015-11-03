package com.ozstrategy.dao.test;

import com.ozstrategy.alipay.config.AlipayConfig;
import com.ozstrategy.alipay.util.AlipayNotify;
import com.ozstrategy.dao.user.RoleDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.recharge.RechargeManager;
import com.ozstrategy.service.system.ActivityUserManager;
import com.ozstrategy.service.system.ApplicationConfigManager;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.service.user.UserManager;
import org.apache.commons.lang3.StringUtils;
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
    private ActivityUserManager activityUserManager;
    @Autowired
    private RechargeManager rechargeManager;






    @Test
    public void testPasswordEncoder(){
        Map map=new HashMap();
        map.put("activityId",1);

        Long count = activityUserManager.findByNamedQueryClass("getUsersCount", Long.class, map);
        System.out.println(count);

    }
    @Autowired
    ApplicationConfigManager applicationConfigManager;

    @Test
    public void testGet(){
        String mobile_rsa=applicationConfigManager.getValue("mobile_rsa");
        String pid=applicationConfigManager.getValue("pid");
        Map<String,String> map=new HashMap<String, String>();
        String str="body=coin&buyer_email=759330841@qq.com&buyer_id=2088002823902855&discount=0.00&" +
                "gmt_create=2015-08-09 13:16:01&" +
                "gmt_payment=2015-08-09 13:16:01&" +
                "is_total_fee_adjust=N&notify_id=9fb029146fc40db45ad0b9fc99941e556q&notify_time=2015-08-09 13:16:02&" +
                "notify_type=trade_status_sync&out_trade_no=15080913150058&payment_type=1&price=0.01&quantity=1&" +
                "seller_email=mingjikaoyu@qq.com&" +
                "seller_id=2088021064120536&sign=krSf7WL8wudUHYkNYA838tj8Pu/mm5QWMVbHE+qxbviWezkyXauJVIAXhklrbdEscocXt09kdQHDHj3oqy8G1ru7+k+blwEKXzlfAiKHKpcdusuMge6MSpNyXKSCGrfxrUgvLNlswQoHWdaZFmnfoNFlbp313qKqbXL7+4zSJlQ=&sign_type=RSA&" +
                "subject=coin&total_fee=0.01&trade_no=2015080900001000850063922947&trade_status=TRADE_SUCCESS&use_coupon=N";
        map.put("body","coin");
        map.put("buyer_email","759330841@qq.com");
        map.put("buyer_id","2088002823902855");
        map.put("discount","0.00");
        map.put("gmt_create","2015-08-07 02:03:50");
        map.put("is_total_fee_adjust","Y");
        map.put("notify_id","1877042c965c87c1320468101ac7ec5b6q");
        map.put("notify_time","2015-08-08 02:27:49");
        map.put("notify_type","trade_status_sync");
        map.put("out_trade_no","15080913150058");
        map.put("payment_type","1");
        map.put("price","0.01");
        map.put("quantity","1");
        map.put("seller_email","mingjikaoyu@qq.com");
        map.put("seller_id","2088021064120536");
        map.put("sign","IZenqLXnM6CkEKtGDi5RhIY9osBy5A67cOPfyKYbTM1ccxhfaWOIU+vKhl8gTElpndk6Y5KG5vatbGY8IcInxLpxjgtuT58JZ5BpTXlQKfO8hll9PhEo57MuQqecGrlY3cc0S/49+9ZchBvviPdWcxdBWmCUp0N9AUGhLCcDXEo=");
        map.put("sign_type","RSA");
        map.put("subject","coin");
        map.put("total_fee","0.01");
        map.put("trade_no","2015080700001000850063720630");
        map.put("trade_status","WAIT_BUYER_PAY");
        map.put("use_coupon","N");
        rechargeManager.mobileNoticeSuccess(map);
//        AlipayNotify.verify(map, mobile_rsa, pid);
//        AlipayNotify.verify(map, AlipayConfig.ali_public_key, pid);
//        System.out.println(StringUtils.equals(AlipayConfig.ali_public_key,mobile_rsa));
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
