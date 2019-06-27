package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.google.gson.annotations.SerializedName;

@Entity
public class RankProduct {

    @ColumnInfo
    @SerializedName("id")
    private int id;

    @ColumnInfo
    @SerializedName("view_count")
    private int viewCount;

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
