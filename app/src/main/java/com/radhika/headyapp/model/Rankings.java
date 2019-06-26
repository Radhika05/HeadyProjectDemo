package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rankings {
    @SerializedName("ranking")
    public String ranking;

    public List<RankProduct> getRankProduct() {
        return rankProduct;
    }

    public void setRankProduct(List<RankProduct> rankProduct) {
        this.rankProduct = rankProduct;
    }

    @SerializedName("products")
    public List<RankProduct> rankProduct;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }




}
