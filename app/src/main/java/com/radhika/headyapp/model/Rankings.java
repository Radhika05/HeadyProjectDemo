package com.radhika.headyapp.model;

import androidx.room.Embedded;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Rankings {

    @Embedded
    @SerializedName("ranking")
    private String ranking;


    @SerializedName("products")
    private List<RankProduct> rankProduct;

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
