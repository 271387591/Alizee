package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.CreditsDetail;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-25.
*/
public class CreditsDetailCommand {
    private Long id;
    private Double oldNum;
    private Double num;
    private String optNickName;
    private String nickName;
    private Long optId;
    private Long userId;
    private String optMobile;
    private Date createDate;
    private String mobile;
    public CreditsDetailCommand() {
    }
    public CreditsDetailCommand(CreditsDetail model) {
        this.id=model.getId();
        this.oldNum=model.getOldNum();
        this.num=model.getNum();
        this.optNickName=model.getOptNickName();
        this.nickName=model.getNickName();
        this.optId=model.getOptId();
        this.userId=model.getUserId();
        this.optMobile=model.getOptMobile();
        this.createDate=model.getCreateDate();
        this.mobile=model.getMobile();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getOldNum() {
        return oldNum;
    }
    public void setOldNum(Double oldNum) {
        this.oldNum = oldNum;
    }
    public Double getNum() {
        return num;
    }
    public void setNum(Double num) {
        this.num = num;
    }
    public String getOptNickName() {
        return optNickName;
    }
    public void setOptNickName(String optNickName) {
        this.optNickName = optNickName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Long getOptId() {
        return optId;
    }
    public void setOptId(Long optId) {
        this.optId = optId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getOptMobile() {
        return optMobile;
    }
    public void setOptMobile(String optMobile) {
        this.optMobile = optMobile;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
