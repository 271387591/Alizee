package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Food;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-21.
*/
public class FoodCommand {
    private String picPath;
    private Long id;
    private String title;
    private String description;
    private String picName;
    private Date createDate;
    private String url;
    private Integer comment;
    private Integer commend;
    public FoodCommand() {
    }
    public FoodCommand(Food model) {
        this.picPath=model.getPicPath();
        this.id=model.getId();
        this.title=model.getTitle();
        this.description=model.getDescription();
        this.picName=model.getPicName();
        this.createDate=model.getCreateDate();
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

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getCommend() {
        return commend;
    }

    public void setCommend(Integer commend) {
        this.commend = commend;
    }
}
