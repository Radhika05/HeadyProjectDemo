package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class RankProduct {

    @ColumnInfo
    private int catergory_Id;

    public int getRank_product_id_local() {

        return rank_product_id_local;
    }

    public void setRank_product_id_local(int rank_product_id_local) {
        this.rank_product_id_local = rank_product_id_local;
    }

    @PrimaryKey(autoGenerate = true)
    private int rank_product_id_local;

    @ColumnInfo
    @SerializedName("id")
    private int id;

    public int getRankings_id() {
        return rankings_id;
    }

    public void setRankings_id(int rankings_id) {
        this.rankings_id = rankings_id;
    }

    private int rankings_id;

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

    public int getCatergory_Id() {
        return catergory_Id;
    }

    public void setCatergory_Id(int catergory_Id) {
        this.catergory_Id = catergory_Id;
    }
}
