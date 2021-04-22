package com.mukeri.pearassignment.model;

public class CouponModel {

    String description;
    String subdescription;
    String couponid;


    public CouponModel()
    {

    }

    public CouponModel(String description, String subdescription, String couponid) {
        this.description = description;
        this.subdescription = subdescription;
        this.couponid = couponid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubdescription() {
        return subdescription;
    }

    public void setSubdescription(String subdescription) {
        this.subdescription = subdescription;
    }

    public String getCouponid() {
        return couponid;
    }

    public void setCouponid(String couponid) {
        this.couponid = couponid;
    }
}
