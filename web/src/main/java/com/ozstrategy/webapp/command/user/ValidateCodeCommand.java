package com.ozstrategy.webapp.command.user;
import com.ozstrategy.model.user.ValidateCode;

import java.util.Date;

/**
* Created by lihao1 on 2015-06-10.
*/
public class ValidateCodeCommand {
    private Long id;
    private Date loseDate;
    private String code;
    private String mobile;
    public ValidateCodeCommand() {
    }
    public ValidateCodeCommand(ValidateCode model) {
        this.id=model.getId();
        this.loseDate=model.getLoseDate();
        this.code=model.getCode();
        this.mobile=model.getMobile();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getLoseDate() {
        return loseDate;
    }
    public void setLoseDate(Date loseDate) {
        this.loseDate = loseDate;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
