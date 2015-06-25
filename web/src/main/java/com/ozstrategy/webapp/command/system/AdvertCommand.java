package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Advert;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-18.
*/
public class AdvertCommand {
    private String picPath;
    private Long id;
    private Boolean enabled;
    private String title;
    private String description;
    private Date createDate;
    private String picName;
    private String url;
    public AdvertCommand() {
    }
    public AdvertCommand(Advert model) {
        this.picPath=model.getPicPath();
        this.id=model.getId();
        this.enabled=model.getEnabled();
        this.title=model.getTitle();
        this.description=model.getDescription();
        this.createDate=model.getCreateDate();
        this.picName=model.getPicName();
        this.url=model.getUrl();
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
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
