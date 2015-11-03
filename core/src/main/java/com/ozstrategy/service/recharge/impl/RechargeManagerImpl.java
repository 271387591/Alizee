package com.ozstrategy.service.recharge.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.recharge.ConsumeDetailDao;
import com.ozstrategy.dao.recharge.RechargeDao;
import com.ozstrategy.dao.system.ApplicationConfigDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.model.recharge.ConsumeDetail;
import com.ozstrategy.model.recharge.ConsumeType;
import com.ozstrategy.model.recharge.Recharge;
import com.ozstrategy.model.recharge.RechargeStatus;
import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.OrderNoInstance;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.recharge.RechargeManager;
import com.ozstrategy.util.Base64Utils;
import com.ozstrategy.util.RSAUtils;
import com.ozstrategy.util.ThreeDESUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("rechargeManager")
public class RechargeManagerImpl extends BaseManagerImpl<Recharge> implements RechargeManager {
    @Autowired
    private RechargeDao rechargeDao;
    @Autowired
    private ApplicationConfigDao applicationConfigDao;
    @Autowired
    private OrderNoInstance orderNoInstance;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ConsumeDetailDao consumeDetailDao;
    Log log= LogFactory.getLog(RechargeManagerImpl.class);



    @Override
    public BaseDao<Recharge> baseDao() {
        return rechargeDao;
    }

    public String createOrder(Recharge recharge) {
        Map<String,Object> map=new HashMap<String, Object>();
        Calendar ca=Calendar.getInstance();
        ca.setTime(new Date());
        Integer loseMin=30;
        String orderLoseTime=applicationConfigDao.getValue("orderLoseTime");
        if(orderLoseTime!=null){
            loseMin=Integer.parseInt(orderLoseTime);
        }
        ca.add(Calendar.MINUTE,loseMin);
        Date loseDate = ca.getTime();
        recharge.setLoseDate(loseDate);
        recharge.setStatus(RechargeStatus.NoPay.ordinal());
        rechargeDao.save(recharge);
        String orderNo=orderNoInstance.orderNo(recharge);
        recharge.setRechargeNo(orderNo);
        rechargeDao.update(recharge);
        try{
            String publicKey=applicationConfigDao.getValue("publicKey");
            String privateKey=applicationConfigDao.getValue("privateKey");
            String notify_url=applicationConfigDao.getValue("notify_url");
            String pid=applicationConfigDao.getValue("pid");
            String seller_email=applicationConfigDao.getValue("seller_email");
            String mobile_rsa=applicationConfigDao.getValue("mobile_rsa");
//            map.put("publicKey",publicKey);
            Map<String,Object> data=new HashMap<String, Object>();
            data.put("id",recharge.getId());
            data.put("orderNo",recharge.getRechargeNo());
            data.put("money",recharge.getMoney());
            data.put("notify_url",notify_url);
            data.put("seller_email",seller_email);
            data.put("mobile_rsa",mobile_rsa);
            data.put("pid",pid);
//            String dataStr=new ObjectMapper().writeValueAsString(data);
            byte[] databyte=new ObjectMapper().writeValueAsBytes(data);
//            byte[] dataStrData = dataStr.getBytes();
//            byte[] encodedData = RSAUtils.encryptByPrivateKey(dataStrData, privateKey);
            map.put("bills", Base64Utils.encode(databyte));
//            map.put("bills", dataStr);

            byte[] retByte=new ObjectMapper().writeValueAsBytes(map);

            String retStr=Base64Utils.encode(ThreeDESUtils.encrypt(retByte));
            return retStr;

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void cancelOrders() {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_loseDate_LE", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        map.put("Q_status_EQ", RechargeStatus.NoPay.ordinal());
        rechargeDao.deleteByParam(map);
    }
    public void mobileNoticeSuccess(Map<String, String> map) {
        String out_trade_no= map.get("out_trade_no");
        String trade_no= map.get("trade_no");
        log.info("mobileNoticeFail==="+out_trade_no+",trade_no=="+trade_no);
        for(String key:map.keySet()){
            log.info("mobileNoticeSuccess::"+key+"=="+map.get(key));
        }
        if(StringUtils.isNotEmpty(out_trade_no)){
            Map<String,Object> orderMap=new HashMap<String, Object>();
            orderMap.put("Q_rechargeNo_EQ",out_trade_no);
            Recharge recharge=rechargeDao.getByParam(orderMap);
            if(recharge!=null && recharge.getStatus()!=RechargeStatus.Pay.ordinal()){
                recharge.setStatus(RechargeStatus.Pay.ordinal());
                recharge.setLastUpdateDate(new Date());
                rechargeDao.update(recharge);
                ConsumeDetail detail=new ConsumeDetail();
                detail.setCredits(recharge.getCredits());
                detail.setUserId(recharge.getUserId());
                detail.setCreateDate(new Date());
                detail.setType(ConsumeType.Plus.ordinal());
                consumeDetailDao.save(detail);
                User user=userDao.get(recharge.getUserId());
                user.setCredits(user.getCredits()+recharge.getCredits());
                userDao.update(user);
            }
        }

    }

    public void mobileNoticeFail(Map<String, String> map) {
        String out_trade_no= map.get("out_trade_no");
        String trade_no= map.get("trade_no");
        String trade_status= map.get("trade_status");
        String fail_details=map.get("fail_details");
        for(String key:map.keySet()){
            log.info("mobileNoticeFail::"+key+"=="+map.get(key));
        }

        if(StringUtils.isNotEmpty(out_trade_no)){
            Map<String,Object> orderMap=new HashMap<String, Object>();
            orderMap.put("Q_rechargeNo_EQ",out_trade_no);
            Recharge recharge=rechargeDao.getByParam(orderMap);
            if(recharge!=null){
                recharge.setStatus(RechargeStatus.Fail.ordinal());
                recharge.setLastUpdateDate(new Date());
                recharge.setDetails(trade_status);
                rechargeDao.update(recharge);
            }
        }
    }
}
