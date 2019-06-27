package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;
import java.util.List;

@Entity
public class Products {

    @ColumnInfo
    @SerializedName("id")
    private String id;

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @ColumnInfo
    @SerializedName("date_added")
    private String date_product;

    @Ignore
    @SerializedName("variants")
    private List<Variants> variants;

    @Ignore
    @SerializedName("tax")
    private Tax tax;

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
