package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("date_added")
    public String date_product;

    @SerializedName("variants")
    public List<Variants> variants;

    @SerializedName("tax")
    public Tax tax;

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_product() {
        return date_product;
    }

    public void setDate_product(String date_product) {
        this.date_product = date_product;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }


}
