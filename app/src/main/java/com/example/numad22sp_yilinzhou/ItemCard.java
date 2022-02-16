package com.example.numad22sp_yilinzhou;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener {

    private final String linkName;
    private final String linkUrl;

    //Constructor
    public ItemCard(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
    }

    //Getters for the name and url
    public String getLinkUrl() {
        return linkUrl;
    }

    public String getLinkName() {
        return linkName;
    }


    @Override
    public void onItemClick(int position) {

    }


}