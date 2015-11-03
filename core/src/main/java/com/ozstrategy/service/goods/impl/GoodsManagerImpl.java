package com.ozstrategy.service.goods.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.goods.GoodsCertificateDao;
import com.ozstrategy.dao.goods.GoodsDao;
import com.ozstrategy.dao.goods.GoodsOrderDao;
import com.ozstrategy.dao.goods.MerchantDao;
import com.ozstrategy.dao.system.CertificateDao;
import com.ozstrategy.dao.user.UserDao;
import com.ozstrategy.exception.GoodsNotHaveException;
import com.ozstrategy.exception.UserCriditsNotHaveException;
import com.ozstrategy.model.goods.Goods;
import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.goods.GoodsOrder;
import com.ozstrategy.model.goods.Merchant;
import com.ozstrategy.model.system.Certificate;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.CertNoInstance;
import com.ozstrategy.service.GoodsOrderNoInstance;
import com.ozstrategy.service.goods.GoodsCertificateManager;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.goods.GoodsManager;
import com.ozstrategy.service.system.CertificateManager;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("goodsManager")
public class GoodsManagerImpl extends BaseManagerImpl<Goods> implements GoodsManager {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CertNoInstance certNoInstance;
    @Autowired
    private GoodsOrderNoInstance goodsOrderNoInstance;
    @Autowired
    private GoodsCertificateDao goodsCertificateDao;
    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private GoodsOrderDao goodsOrderDao;
    @Autowired
    private CertificateDao certificateDao;








    @Override
    public BaseDao<Goods> baseDao() {
        return goodsDao;
    }

    public Map<String,Object> purchase(Goods goods,Integer num,User user) throws GoodsNotHaveException,UserCriditsNotHaveException {
        Map<String,Object> resultMap=new HashMap<String, Object>();
//        Goods goods=goodsDao.get(id);
        Merchant merchant = merchantDao.get(goods.getMerchantId());
        if(goods.getNum()<num){
            throw new GoodsNotHaveException("goods has none");
        }
        if(goods.getPrice()*num>user.getCredits()){
            throw new UserCriditsNotHaveException("user has no enough credits");
        }
        goods.setNum(goods.getNum()-num);
        goodsDao.update(goods);
        Double cridits=user.getCredits()-goods.getPrice()*num;
        user.setCredits(cridits);
        userDao.update(user);

        GoodsOrder goodsOrder=new GoodsOrder();
        goodsOrder.setGoodsId(goods.getId());
        goodsOrder.setMerchantId(goods.getMerchantId());
        goodsOrder.setUserId(user.getId());
        goodsOrder.setCreateDate(new Date());
        goodsOrder.setAllCredits(goods.getPrice()*num);
        goodsOrder.setPrice(goods.getPrice());
        goodsOrder.setNum(num);
        goodsOrderDao.save(goodsOrder);
        goodsOrder.setOrderNo(goodsOrderNoInstance.orderNo(goodsOrder));
        goodsOrderDao.update(goodsOrder);
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        for(int i=0;i<num;i++){
            GoodsCertificate certificate=new GoodsCertificate();
            certificate.setUserId(user.getId());
            certificate.setCreateDate(new Date());
            certificate.setGoodsId(goods.getId());
            certificate.setMerchantId(goods.getMerchantId());
            certificate.setCredits(goods.getPrice());
            certificate.setOrderId(goodsOrder.getId());

//            Date fixDate=goods.getFixedDate();
//            Integer trends=goods.getTrends();
//            if(fixDate!=null){
//                certificate.setEndDate(fixDate);
//            }else if(trends!=null){
//                Calendar calendar=Calendar.getInstance();
//                calendar.setTime(new Date());
//                calendar.add(Calendar.DAY_OF_YEAR,trends);
//                certificate.setEndDate(calendar.getTime());
//            }

            Integer trends=goods.getTrends();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR,trends);
            certificate.setEndDate(calendar.getTime());

//
//            List<Certificate> certificates=certificateDao.listAll();
//            if(certificates!=null && certificates.size()>0){
//                Certificate certificate1=certificates.get(0);
//                String endDate=certificate1.getEndDate();
//                Integer type=certificate1.getType();
//                if(type==1){
//                    try {
//                        certificate.setEndDate(DateUtils.parseDate(endDate,"yyyy-MM-dd HH:mm:ss"));
//                    } catch (ParseException e) {
//                    }
//                }else{
//                    Calendar calendar=Calendar.getInstance();
//                    calendar.setTime(new Date());
//                    calendar.add(Calendar.DAY_OF_YEAR,Integer.parseInt(endDate));
//                    certificate.setEndDate(calendar.getTime());
//                }
//            }
            goodsCertificateDao.save(certificate);
            certificate.setCertNo(certNoInstance.orderNo(certificate));
            goodsCertificateDao.update(certificate);
            Map<String,Object> objectMap=new HashMap<String, Object>();
            objectMap.put("id", certificate.getId());
            objectMap.put("createDate", certificate.getCreateDate());
            objectMap.put("endDate", certificate.getEndDate());
            objectMap.put("certNo", certificate.getCertNo());
            objectMap.put("userId", certificate.getUserId());
            list.add(objectMap);
        }
        resultMap.put("goodsId", goodsOrder.getGoodsId());
        resultMap.put("merchantId", goodsOrder.getMerchantId());
        resultMap.put("mobile",user.getMobile());
        resultMap.put("nickName",user.getNickName());
        resultMap.put("goodsName",goods.getName());
        resultMap.put("goodsPic",goods.getUrl());
        resultMap.put("merchantName",merchant.getName());
        resultMap.put("merchantAddress",merchant.getAddress());
        resultMap.put("merchantPhone",merchant.getPhone());
        resultMap.put("num",num);
        resultMap.put("allCredits",goodsOrder.getAllCredits());
        resultMap.put("price",goodsOrder.getPrice());
        resultMap.put("certs",list);
        return  resultMap;

    }
}
