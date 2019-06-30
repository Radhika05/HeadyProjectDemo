package com.radhika.headyapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Rankings {

    @SerializedName("ranking")
    private String ranking;

    @PrimaryKey(autoGenerate = true)
    private int ids;

    @Ignore
    @SerializedName("products")
    private List<RankProduct> rankProduct;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public List<RankProduct> getRankProduct() {
        return rankProduct;
    }

    public void setRankProduct(List<RankProduct> rankProduct) {
        this.rankProduct = rankProduct;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
