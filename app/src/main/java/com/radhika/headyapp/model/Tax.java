package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Tax {

    @PrimaryKey(autoGenerate = true)
    private int ids;

    @ColumnInfo(name = "product_id")
    private int product_id;

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @ColumnInfo
    @SerializedName("value")
    private String value;


    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
