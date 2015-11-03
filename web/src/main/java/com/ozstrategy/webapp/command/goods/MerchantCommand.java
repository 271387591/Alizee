package com.ozstrategy.webapp.command.goods;
import com.ozstrategy.model.goods.Merchant;
/**
* Created by lihao1 on 2015-06-30.
*/
public class MerchantCommand {
    private Long id;
    private String phone;
    private String address;
    private Long userId;
    private String name;
    private Double credits;
    private String description;
    public MerchantCommand() {
    }
    public MerchantCommand(Merchant model) {
        this.id=model.getId();
        this.phone=model.getPhone();
        this.address=model.getAddress();
        this.userId=model.getUserId();
        this.name=model.getName();
        this.credits=model.getCredits();
        this.description=model.getDescription();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
