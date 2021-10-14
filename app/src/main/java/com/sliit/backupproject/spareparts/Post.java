package com.sliit.backupproject.spareparts;

import com.google.gson.annotations.SerializedName;

public class Post {

    private float price;

    @SerializedName("prices")
    private String text;

    public float getPrice() {
        return price;
    }

    public String getText() {
        return text;
    }
}
