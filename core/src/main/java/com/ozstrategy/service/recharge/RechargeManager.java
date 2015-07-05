package com.ozstrategy.service.recharge;

import com.ozstrategy.model.recharge.Recharge;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.BaseManager;

import java.util.Map;

/**
* Created by lihao1 on 2015-07-02.
*/
public interface RechargeManager extends BaseManager<Recharge> {
    String createOrder(Recharge recharge);
    void cancelOrders();
    void mobileNoticeSuccess(Map<String,String> map);
    void mobileNoticeFail(Map<String,String> map);
}
