package com.ozstrategy.service;

import com.ozstrategy.model.goods.GoodsOrder;
import com.ozstrategy.model.recharge.Recharge;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihao1 on 5/28/15.
 */
public class GoodsOrderNoInstance {
    private volatile static GoodsOrderNoInstance instance=null;
    private static String date ;
    private GoodsOrderNoInstance(){}
    public static GoodsOrderNoInstance getInstance(){
        if(instance==null){
            synchronized (GoodsOrderNoInstance.class){
                if(instance==null){
                    instance=new GoodsOrderNoInstance();
                }
            }
        }
        return instance;
    }
    public synchronized static String orderNo(GoodsOrder order){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
        }
        Long orderNo = Long.parseLong((date)) * 10000;
        orderNo += order.getId();;
        return "D"+orderNo;
    }

}
