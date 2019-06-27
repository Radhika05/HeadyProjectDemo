package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Variants {


    @PrimaryKey(autoGenerate = true)
    private int ids;

    @ColumnInfo
    @SerializedName("id")
    private String id;

    @ColumnInfo
    @SerializedName("color")
    private String color;

    @ColumnInfo
    @SerializedName("size")
    private String size;

    @ColumnInfo
    @SerializedName("price")
    private String price;


    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
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
