package com.ozstrategy.model.goods;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

import java.util.Date;

/**
 * Created by lihao1 on 8/4/15.
 */
@Table(name="t_goodsorder")
@NamedQueries({
        @NamedQuery(name = "getGoodsOrders",query = "select r.*,ur.name as merchantName,ur.phone,ur.address,ur.description as merchantDes,tg.name as goodsName,tg.url as goodsUrl,tu.mobile,tu.portraitUrl from t_goodsorder r join t_merchant ur on r.merchantId=ur.id join t_goods tg on tg.id=r.goodsId join t_user tu on tu.id=r.userId   where 1=1"),
        @NamedQuery(name = "getGoodsOrdersCount",query = "select count(*) from t_goodsorder r join t_merchant ur on r.merchantId=ur.id join t_goods tg on tg.id=r.goodsId join t_user tu on tu.id=r.userId   where 1=1")
})
public class GoodsOrder extends BaseEntity{
    @Id
    private Long id;
    private String orderNo;
    private Date createDate;
    private Double allCredits=new Double(0);
    private Long goodsId;
    private Long merchantId;
    private Double price;
    private Integer num;
    private Long userId;

    public GoodsOrder() {
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

    public Double getAllCredits() {
        return allCredits;
    }

    public void setAllCredits(Double allCredits) {
        this.allCredits = allCredits;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
