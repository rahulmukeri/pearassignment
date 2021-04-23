package com.mukeri.pearassignment.model;

import java.util.List;

public class AllCategory {

    String categoryTitle;
    boolean iscollapsed;
    String layouttype;
    List<CategoryItem> categoryItemList;
    List<CouponModel> couponModels;
    String itemcount;

    public AllCategory(String categoryTitle, boolean iscollapsed, String layouttype, List<CategoryItem> categoryItemList, String itemcount) {
        this.categoryTitle = categoryTitle;
        this.iscollapsed = iscollapsed;
        this.layouttype = layouttype;
        this.categoryItemList = categoryItemList;
        this.itemcount = itemcount;
    }

    public AllCategory(List<CouponModel> couponModels, String layouttype) {
        this.couponModels = couponModels;
        this.layouttype = layouttype;
    }

    public String getItemcount() {
        return itemcount;
    }

    public void setItemcount(String itemcount) {
        this.itemcount = itemcount;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public boolean isIscollapsed() {
        return iscollapsed;
    }

    public void setIscollapsed(boolean iscollapsed) {
        this.iscollapsed = iscollapsed;
    }

    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public List<CouponModel> getCouponModels() {
        return couponModels;
    }

    public void setCouponModels(List<CouponModel> couponModels) {
        this.couponModels = couponModels;
    }

    public String getLayouttype() {
        return layouttype;
    }

    public void setLayouttype(String layouttype) {
        this.layouttype = layouttype;
    }
}
