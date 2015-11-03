package com.ozstrategy.model.goods;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

import java.util.Date;

/**
 * Created by lihao1 on 8/31/15.
 */
@Table(name = "t_threegoods")
@NamedQueries({
        @NamedQuery(name = "getGoods",query = "select t.*,u.mobile,u.portraitUrl,u.nickName from t_threegoods t join t_user u on t.userId=u.id where 1=1"),
        @NamedQuery(name = "getGoodsCount",query = "select count(*) from t_threegoods t join t_user u on t.userId=u.id where 1=1")
})
public class ThreeGoods extends BaseEntity{
    @Id
    private Long id;
    private String orderNo;
    private Date createDate;
    private Long channelId;
    private String channelName;
    private String channelNo;
    private String goodsName;
    private Double goodsPrice;
    private Long userId;
    private Integer num;

    public ThreeGoods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
