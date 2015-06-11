package com.ozstrategy.model.system;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * Created by lihao1 on 5/29/15.
 */
@Table(name = "t_applicationconfig")
public class ApplicationConfig extends BaseEntity{
    @Id
    private Long id;
    private String systemKey;
    private String systemValue;

    public ApplicationConfig() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getSystemValue() {
        return systemValue;
    }

    public void setSystemValue(String systemValue) {
        this.systemValue = systemValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ApplicationConfig user = (ApplicationConfig) o;
        return new EqualsBuilder()
                .append(id, user.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .hashCode();

    }
}
