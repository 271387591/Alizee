package com.ozstrategy.model.system;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

/**
 * Created by lihao1 on 7/29/15.
 */
@Table(name="t_certificate")
public class Certificate extends BaseEntity{
    @Id
    private Long id;
    private String endDate;
    private Integer type;//0固定

    public Certificate() {
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
