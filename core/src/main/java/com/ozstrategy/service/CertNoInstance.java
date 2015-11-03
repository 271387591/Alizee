package com.ozstrategy.service;

import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.recharge.Recharge;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihao1 on 5/28/15.
 */
public class CertNoInstance {
    private volatile static CertNoInstance instance=null;
    private static String date ;
    private CertNoInstance(){}
    public static CertNoInstance getInstance(){
        if(instance==null){
            synchronized (CertNoInstance.class){
                if(instance==null){
                    instance=new CertNoInstance();
                }
            }
        }
        return instance;
    }
    public synchronized static String orderNo(GoodsCertificate order){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
        }
        Long orderNo = Long.parseLong((date)) * 10000;
        orderNo += order.getId();;
        return "G"+orderNo;
    }

}
