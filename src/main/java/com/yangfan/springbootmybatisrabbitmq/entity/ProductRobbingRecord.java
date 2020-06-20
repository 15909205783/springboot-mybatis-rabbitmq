package com.yangfan.springbootmybatisrabbitmq.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductRobbingRecord implements Serializable {
    private Integer id;

    private String mobile;

    private Integer productId;

    private Date robbingTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getRobbingTime() {
        return robbingTime;
    }

    public void setRobbingTime(Date robbingTime) {
        this.robbingTime = robbingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ProductRobbingRecord{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", productId=" + productId +
                ", robbingTime=" + robbingTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
