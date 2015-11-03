package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Channel;

import java.util.Date;

/**
* Created by lihao1 on 2015-08-20.
*/
public class ChannelCommand {
    private Long id;
    private String name;
    private String channelNo;
    private Date createDate;
    private Integer type;
    public ChannelCommand() {
    }
    public ChannelCommand(Channel model) {
        this.id=model.getId();
        this.name=model.getName();
        this.channelNo=model.getChannelNo();
        this.createDate=model.getCreateDate();
        this.type=model.getType();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getChannelNo() {
        return channelNo;
    }
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
