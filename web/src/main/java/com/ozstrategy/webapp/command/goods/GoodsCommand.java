package com.ozstrategy.webapp.command.goods;
import com.ozstrategy.model.goods.Goods;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-30.
*/
public class GoodsCommand {
    private Long id;
    private Boolean enabled;
    private Integer num;
    private Double price;
    private String description;
    private Long userId;
    private String name;
    private Long merchantId;
    private Date createDate;
    private Date lastUpdateDate;
    private String picPath;
    private String picName;
    private String url;
    private Boolean purchase=Boolean.FALSE;

    private Date endDate;
    private Date currentDate;
    private Date fixedDate;
    private Integer trends;
    private String fixedDateStr;
    private String logoPath;
    private String logoUrl;
    private String logoName;

    public GoodsCommand() {
    }
    public GoodsCommand(Goods model) {
        this.id=model.getId();
        this.enabled=model.getEnabled();
        this.num=model.getNum();
        this.price=model.getPrice();
        this.description=model.getDescription();
        this.userId=model.getUserId();
        this.name=model.getName();
        this.merchantId=model.getMerchantId();
        this.createDate=model.getCreateDate();
        this.lastUpdateDate=model.getLastUpdateDate();
        this.picPath=model.getPicPath();
        this.picName=model.getPicName();
        this.url=model.getUrl();
        this.purchase=model.getPurchase();
        this.trends=model.getTrends();
        this.fixedDate=model.getFixedDate();
        this.logoName=model.getLogoName();
        this.logoPath=model.getLogoPath();
        this.logoUrl=model.getLogoUrl();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getPurchase() {
        return purchase;
    }

    public void setPurchase(Boolean purchase) {
        this.purchase = purchase;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(Date fixedDate) {
        this.fixedDate = fixedDate;
    }

    public Integer getTrends() {
        return trends;
    }

    public void setTrends(Integer trends) {
        this.trends = trends;
    }

    public String getFixedDateStr() {
        return fixedDateStr;
    }

    public void setFixedDateStr(String fixedDateStr) {
        this.fixedDateStr = fixedDateStr;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
}
