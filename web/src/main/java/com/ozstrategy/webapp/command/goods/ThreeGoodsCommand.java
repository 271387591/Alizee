package com.ozstrategy.webapp.command.goods;
import com.ozstrategy.model.goods.ThreeGoods;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-31.
*/
public class ThreeGoodsCommand {
    private Long id;
    private String channelName;
    private String orderNo;
    private Long channelId;
    private Long userId;
    private Double goodsPrice;
    private Date createDate;
    private String goodsName;
    private String channelNo;
    private Integer num;
    public ThreeGoodsCommand() {
    }
    public ThreeGoodsCommand(ThreeGoods model) {
        this.id=model.getId();
        this.channelName=model.getChannelName();
        this.orderNo=model.getOrderNo();
        this.channelId=model.getChannelId();
        this.userId=model.getUserId();
        this.goodsPrice=model.getGoodsPrice();
        this.createDate=model.getCreateDate();
        this.goodsName=model.getGoodsName();
        this.channelNo=model.getChannelNo();
        this.num=model.getNum();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Long getChannelId() {
        return channelId;
    }
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Double getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
