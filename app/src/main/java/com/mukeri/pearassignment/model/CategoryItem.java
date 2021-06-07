package com.mukeri.pearassignment.model;

public class CategoryItem {

// here i am taking only image url. and this is as integer because i am using it from drawable file.

    String itemId;
    String itemname;
    String price;
    String ACprice;
    String discount;
    Integer imageUrl;
    boolean showtootip;


    public CategoryItem()
    {

    }


    public CategoryItem(String itemId, String itemname, String price, String ACprice, String discount, Integer imageUrl, boolean showtootip) {
        this.itemId = itemId;
        this.itemname = itemname;
        this.price = price;
        this.ACprice = ACprice;
        this.discount = discount;
        this.imageUrl = imageUrl;
        this.showtootip = showtootip;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getACprice() {
        return ACprice;
    }

    public void setACprice(String ACprice) {
        this.ACprice = ACprice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isShowtootip() {
        return showtootip;
    }

    public void setShowtootip(boolean showtootip) {
        this.showtootip = showtootip;
    }
}
