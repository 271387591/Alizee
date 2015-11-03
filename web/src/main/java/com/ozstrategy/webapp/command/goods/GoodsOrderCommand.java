package com.ozstrategy.webapp.command.goods;
import com.ozstrategy.model.goods.GoodsOrder;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-04.
*/
public class GoodsOrderCommand {
    private Long id;
    private Long goodsId;
    private Integer num;
    private Double price;
    private String orderNo;
    private Double allCredits;
    private Long userId;
    private Long merchantId;
    private Date createDate;
    public GoodsOrderCommand() {
    }
    public GoodsOrderCommand(GoodsOrder model) {
        this.id=model.getId();
        this.goodsId=model.getGoodsId();
        this.num=model.getNum();
        this.price=model.getPrice();
        this.orderNo=model.getOrderNo();
        this.allCredits=model.getAllCredits();
        this.userId=model.getUserId();
        this.merchantId=model.getMerchantId();
        this.createDate=model.getCreateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Double getAllCredits() {
        return allCredits;
    }
    public void setAllCredits(Double allCredits) {
        this.allCredits = allCredits;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
