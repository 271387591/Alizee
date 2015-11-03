package com.ozstrategy.webapp.command.goods;
import com.ozstrategy.model.goods.GoodsCertificate;

import java.util.Date;

/**
* Created by lihao1 on 2015-07-29.
*/
public class GoodsCertificateCommand {
    private Long id;
    private Long goodsId;
    private Long userId;
    private String certNo;
    private Long merchantId;
    private Date endDate;
    private Date createDate;
    public GoodsCertificateCommand() {
    }
    public GoodsCertificateCommand(GoodsCertificate model) {
        this.id=model.getId();
        this.goodsId=model.getGoodsId();
        this.userId=model.getUserId();
        this.certNo=model.getCertNo();
        this.merchantId=model.getMerchantId();
        this.endDate=model.getEndDate();
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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCertNo() {
        return certNo;
    }
    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
    public Long getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
