package com.ozstrategy.webapp.command.system;
import com.ozstrategy.model.system.About;
/**
* Created by lihao1 on 2015-06-18.
*/
public class AboutCommand {
    private Long id;
    private String companyAddress;
    private String companyEmail;
    private String companyPhone;
    private String companyMobile;
    private String companyName;
    private String companyInfo;
    private String companyQQ;
    private String companyProduct;
    public AboutCommand() {
    }
    public AboutCommand(About model) {
        this.id=model.getId();
        this.companyAddress=model.getCompanyAddress();
        this.companyEmail=model.getCompanyEmail();
        this.companyPhone=model.getCompanyPhone();
        this.companyMobile=model.getCompanyMobile();
        this.companyName=model.getCompanyName();
        this.companyInfo=model.getCompanyInfo();
        this.companyQQ=model.getCompanyQQ();
        this.companyProduct=model.getCompanyProduct();
    }
    public About toAbout(){
        About about=new About();
        about.setCompanyAddress(this.getCompanyAddress());
        about.setCompanyEmail(this.getCompanyEmail());
        about.setCompanyInfo(this.getCompanyInfo());
        about.setCompanyMobile(this.getCompanyMobile());
        about.setCompanyName(this.getCompanyName());
        about.setCompanyPhone(this.getCompanyPhone());
        about.setCompanyProduct(this.getCompanyProduct());
        about.setCompanyQQ(this.getCompanyQQ());
        about.setId(this.getId());
        return about;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getCompanyEmail() {
        return companyEmail;
    }
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    public String getCompanyPhone() {
        return companyPhone;
    }
    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }
    public String getCompanyMobile() {
        return companyMobile;
    }
    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyInfo() {
        return companyInfo;
    }
    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }
    public String getCompanyQQ() {
        return companyQQ;
    }
    public void setCompanyQQ(String companyQQ) {
        this.companyQQ = companyQQ;
    }
    public String getCompanyProduct() {
        return companyProduct;
    }
    public void setCompanyProduct(String companyProduct) {
        this.companyProduct = companyProduct;
    }
}
