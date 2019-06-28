package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class RankProduct {

    @PrimaryKey(autoGenerate = true)
    private int ids;

    @ColumnInfo
    private int catergory_Id;

    @ColumnInfo
    @SerializedName("id")
    private int id;

    @ColumnInfo
    @SerializedName("view_count")
    private int viewCount;


    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

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

    public int getCatergory_Id() {
        return catergory_Id;
    }

    public void setCatergory_Id(int catergory_Id) {
        this.catergory_Id = catergory_Id;
    }


}
