package com.ozstrategy.service;

import com.ozstrategy.model.recharge.Recharge;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihao1 on 5/28/15.
 */
public class OrderNoInstance {
    private volatile static OrderNoInstance instance=null;
    private static String date ;
    private OrderNoInstance(){}
    public static OrderNoInstance getInstance(){
        if(instance==null){
            synchronized (OrderNoInstance.class){
                if(instance==null){
                    instance=new OrderNoInstance();
                }
            }
        }
        return instance;
    }
    public synchronized static String orderNo(Recharge order){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
        }
        Long orderNo = Long.parseLong((date)) * 10000;
        orderNo += order.getId();;
        return orderNo+"";
    }

}
