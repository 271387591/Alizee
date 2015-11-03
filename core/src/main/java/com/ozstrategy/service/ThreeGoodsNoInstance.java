package com.ozstrategy.service;

import com.ozstrategy.model.goods.GoodsCertificate;
import com.ozstrategy.model.goods.ThreeGoods;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihao1 on 5/28/15.
 */
public class ThreeGoodsNoInstance {
    private volatile static ThreeGoodsNoInstance instance=null;
    private static String date ;
    private ThreeGoodsNoInstance(){}
    public static ThreeGoodsNoInstance getInstance(){
        if(instance==null){
            synchronized (ThreeGoodsNoInstance.class){
                if(instance==null){
                    instance=new ThreeGoodsNoInstance();
                }
            }
        }
        return instance;
    }
    public synchronized static String orderNo(ThreeGoods order){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
        }
        Long orderNo = Long.parseLong((date)) * 10000;
        orderNo += order.getId();;
        return "T"+orderNo;
    }

}
