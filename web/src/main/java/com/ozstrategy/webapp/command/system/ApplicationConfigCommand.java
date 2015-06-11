package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.ApplicationConfig;
/**
* Created by lihao1 on 2015-06-10.
*/
public class ApplicationConfigCommand {
    private Long id;
    private String systemValue;
    private String systemKey;
    public ApplicationConfigCommand() {
    }
    public ApplicationConfigCommand(ApplicationConfig model) {
        this.id=model.getId();
        this.systemValue=model.getSystemValue();
        this.systemKey=model.getSystemKey();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSystemValue() {
        return systemValue;
    }
    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }
    public String getSystemKey() {
        return systemKey;
    }
    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }
}
