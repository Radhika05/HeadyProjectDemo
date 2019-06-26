package com.radhika.headyapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("products")
    public List<Products> products;

    @SerializedName("child_categories")
    public List<Integer> child_cat;



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
