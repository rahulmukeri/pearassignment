package com.mukeri.pearassignment.model;

import java.io.Serializable;

public class CardItem implements Serializable {
    private String name;
    private int resId;

    public CardItem() {
    }

    public String getName() {
        return name;
    }

    public CardItem setName(String name) {
        this.name = name;
        return this;
    }

    public int getResId() {
        return resId;
    }

    public CardItem setResId(int resId) {
        this.resId = resId;
        return this;
    }
}
