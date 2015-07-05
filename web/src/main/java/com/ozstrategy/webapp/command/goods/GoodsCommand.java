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
}
