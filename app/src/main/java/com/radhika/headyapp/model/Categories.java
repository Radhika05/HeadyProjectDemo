package com.radhika.headyapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Categories {

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;

    @ColumnInfo
    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("products")
    public List<Products> products;

    @Ignore
    @SerializedName("child_categories")
    private List<Integer> child_cat;

    public List<Integer> getChild_cat() {
        return child_cat;
    }

    public void setChild_cat(List<Integer> child_cat) {
        this.child_cat = child_cat;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
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
}
