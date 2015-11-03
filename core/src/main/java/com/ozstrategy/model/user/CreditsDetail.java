package com.ozstrategy.model.user;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

import java.util.Date;

/**
 * Created by lihao1 on 8/25/15.
 */
@Table(name = "t_creditsdetail")
public class CreditsDetail extends BaseEntity{
    @Id
    private Long id;
    private Double num;
    private String mobile;
    private String nickName;
    private Long userId;
    private Date createDate;
    private Double oldNum;
    private String optMobile;
    private String optNickName;
    private Long optId;

    public CreditsDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getOldNum() {
        return oldNum;
    }

    public void setOldNum(Double oldNum) {
        this.oldNum = oldNum;
    }

    public String getOptMobile() {
        return optMobile;
    }

    public void setOptMobile(String optMobile) {
        this.optMobile = optMobile;
    }

    public String getOptNickName() {
        return optNickName;
    }

    public void setOptNickName(String optNickName) {
        this.optNickName = optNickName;
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }
}
