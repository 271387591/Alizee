package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Activity;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-25.
*/
public class ActivityCommand {
    private String picPath;
    private Long id;
    private String title;
    private String merchantPhone;
    private String merchant;
    private String description;
    private Date endDate;
    private Boolean published;
    private String picName;
    private Date createDate;
    private String url;
    private String merchantAddress;
    public ActivityCommand() {
    }
    public ActivityCommand(Activity model) {
        this.picPath=model.getPicPath();
        this.id=model.getId();
        this.title=model.getTitle();
        this.merchantPhone=model.getMerchantPhone();
        this.merchant=model.getMerchant();
        this.description=model.getDescription();
        this.endDate=model.getEndDate();
        this.published=model.getPublished();
        this.picName=model.getPicName();
        this.createDate=model.getCreateDate();
        this.url=model.getUrl();
        this.merchantAddress=model.getMerchantAddress();
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMerchantPhone() {
        return merchantPhone;
    }
    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Boolean getPublished() {
        return published;
    }
    public void setPublished(Boolean published) {
        this.published = published;
    }
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMerchantAddress() {
        return merchantAddress;
    }
    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }
}
