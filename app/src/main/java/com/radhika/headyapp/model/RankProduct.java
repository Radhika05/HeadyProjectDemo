package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

public class RankProduct {

    @SerializedName("id")
    public int id;
    @SerializedName("view_count")
    public int viewCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


}
