package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainPojo {


    @SerializedName("categories")
    public List<Categories> categories;

    @SerializedName("rankings")
    public List<Rankings>rankings;

    public List<Categories> getCategories() {
        return categories;
    }

    public void setRankings(List<Rankings> rankings) {
        this.rankings = rankings;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
