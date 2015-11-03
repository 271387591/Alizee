package com.ozstrategy.model.goods;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

import java.util.Date;

/**
 * Created by lihao1 on 7/29/15.
 */
@Table(name="t_goodscertificate")
@NamedQueries({
        @NamedQuery(name = "getCerts",query = "select r.*,ur.name,ur.price,ur.logoUrl,u.mobile,u.nickName,now() as currentDate from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id  where r.merchantId=:merchantId"),
        @NamedQuery(name = "getCertsCount",query = "select COUNT(*) from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id where r.merchantId=:merchantId"),
        @NamedQuery(name = "getUserCert",query = "select r.*,ur.name,ur.price,ur.url as goodsUrl,ur.logoUrl,ur.id as goodsId,u.mobile,u.nickName,now() as currentDate,m.name as merchantName,m.address as merchantAddress,m.phone as merchantPhone,m.description as merchantDes from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id join t_merchant m on m.id=r.merchantId  where r.userId=:userId"),
        @NamedQuery(name = "getUserCertCount",query = "select count(*) from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id join t_merchant m on m.id=r.merchantId  where r.userId=:userId"),
        @NamedQuery(name = "getAllCerts",query = "select r.*,ur.name,ur.price,ur.url as goodsUrl,ur.logoUrl,ur.id as goodsId,u.mobile,u.nickName,now() as currentDate,m.name as merchantName,m.address as merchantAddress,m.phone as merchantPhone,m.description as merchantDes from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id join t_merchant m on m.id=r.merchantId  where 1=1"),
        @NamedQuery(name = "getAllCertsCount",query = "select count(*) from t_goodscertificate r join t_goods ur on r.goodsId=ur.id join t_user u on r.userId=u.id join t_merchant m on m.id=r.merchantId  where 1=1")

})
public class GoodsCertificate extends BaseEntity{
    @Id
    private Long id;
    private String certNo;
    private Date createDate;
    private Date endDate;
    private Long goodsId;
    private Long merchantId;
    private Long userId;
    private Boolean  enabled=Boolean.TRUE;
    private Date lastUpdate;
    private Double credits=new Double(0);
    private Long orderId;
    private Long shoperId;
    private String shoperName;
    private String shoperMobile;

    public GoodsCertificate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getShoperId() {
        return shoperId;
    }

    public void setShoperId(Long shoperId) {
        this.shoperId = shoperId;
    }

    public String getShoperName() {
        return shoperName;
    }

    public void setShoperName(String shoperName) {
        this.shoperName = shoperName;
    }

    public String getShoperMobile() {
        return shoperMobile;
    }

    public void setShoperMobile(String shoperMobile) {
        this.shoperMobile = shoperMobile;
    }
}
