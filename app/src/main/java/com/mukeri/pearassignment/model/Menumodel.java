package com.mukeri.pearassignment.model;

import java.util.List;

public class Menumodel {

    String categoryTitle;
    String totalitem;
    boolean iscollapsed;


    public Menumodel()
    {

    }

    public Menumodel(String categoryTitle, String totalitem, boolean iscollapsed) {
        this.categoryTitle = categoryTitle;
        this.totalitem = totalitem;
        this.iscollapsed = iscollapsed;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(String totalitem) {
        this.totalitem = totalitem;
    }

    public boolean isIscollapsed() {
        return iscollapsed;
    }

    public void setIscollapsed(boolean iscollapsed) {
        this.iscollapsed = iscollapsed;
    }
}
