package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.Certificate;
/**
* Created by lihao1 on 2015-07-29.
*/
public class CertificateCommand {
    private Long id;
    private String endDate;
    private Integer type;
    public CertificateCommand() {
    }
    public CertificateCommand(Certificate model) {
        this.id=model.getId();
        this.endDate=model.getEndDate();
        this.type=model.getType();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
}
