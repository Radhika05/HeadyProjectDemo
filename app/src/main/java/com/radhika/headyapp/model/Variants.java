package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

public class Variants {
    @SerializedName("id")
    public String id;

    @SerializedName("color")
    public String color;

    @SerializedName("size")
    public String size;

    @SerializedName("price")
    public String price;

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
