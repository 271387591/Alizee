package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Notice;

import java.util.Date;

/**
* Created by lihao1 on 2015-07-14.
*/
public class NoticeCommand {
    private String content;
    private Long id;
    private Date createDate;
    public NoticeCommand() {
    }
    public NoticeCommand(Notice model) {
        this.content=model.getContent();
        this.id=model.getId();
        this.createDate=model.getCreateDate();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
