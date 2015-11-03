package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.GoodsSetting;
/**
* Created by lihao1 on 2015-07-29.
*/
public class GoodsSettingCommand {
    private Long id;
    private String startDate;
    private String endDate;
    public GoodsSettingCommand() {
    }
    public GoodsSettingCommand(GoodsSetting model) {
        this.id=model.getId();
        this.startDate=model.getStartDate();
        this.endDate=model.getEndDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
