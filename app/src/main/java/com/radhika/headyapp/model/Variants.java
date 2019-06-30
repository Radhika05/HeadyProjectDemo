package com.radhika.headyapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Variants {

    @PrimaryKey@NonNull
    @ColumnInfo
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "product_id")
    private int product_id;

    @ColumnInfo
    @SerializedName("color")
    private String color;

    @ColumnInfo
    @SerializedName("size")
    private String size;

    @ColumnInfo
    @SerializedName("price")
    private String price;


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
