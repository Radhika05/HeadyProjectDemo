package com.radhika.headyapp.model;

import androidx.room.Embedded;

public class TempProduct {
    @Embedded(prefix = "cat_")
    public Categories categories;

    @Embedded
    public Products products;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
